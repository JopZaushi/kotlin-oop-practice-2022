package lab3

interface NoteService {
    val notes: MutableList<Note>

    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note>
    fun getAllTasks(): List<Note>
    fun getAllLinks(): List<Note>

    fun createTextNote(title: String, content: String, date: Date): Note.TextNote
    fun createTask(title: String, content: String, deadline: String, date: Date): Note.Task
    fun createLink(title: String, content: String, url: String, date: Date): Note.Link

    fun sortByTitle(): List<Note>
    fun sortByDate(): List<Note>

    fun removeNote(title: String)

    fun findByTypeAndTitle(type: String, title: String): Note

}

//To make it easier to sort
class Date(val day: Int, val month: Int, val year: Int) {
    override fun toString(): String {
        return "Date of creation: $day.$month.$year"
    }
}

sealed class Note(open val title: String, open val date: Date) {

    class TextNote(override val title: String, private val content: String, override val date: Date) :
        Note(title, date) {
        override fun toString(): String {
            return "TextNote: $title\n" +
                    "$content\n" +
                    "$date\n"
        }

    }

    class Task(
        override val title: String,
        private val content: String,
        private val deadline: String,
        override val date: Date,
    ) : Note(title, date) {
        override fun toString(): String {
            return "Task: $title||Deadline: $deadline\n" +
                    "$content\n" +
                    "$date\n"
        }
    }

    class Link(
        override val title: String,
        private val content: String,
        private val url: String,
        override val date: Date,
    ) : Note(title, date) {
        override fun toString(): String {
            return "Link: $title  $url\n" +
                    "$content\n" +
                    "$date\n"
        }
    }


}

class ApplicationNotes : NoteService {

    override val notes: MutableList<Note> = mutableListOf()

    override fun createTextNote(title: String, content: String, date: Date): Note.TextNote {
        val noteText = Note.TextNote(title, content, date)
        notes.add(noteText)
        return noteText
    }

    override fun createTask(title: String, content: String, deadline: String, date: Date): Note.Task {
        val noteTask = Note.Task(title, content, deadline, date)
        notes.add(noteTask)
        return noteTask
    }

    override fun createLink(title: String, content: String, url: String, date: Date): Note.Link {
        val noteLink = Note.Link(title, content, url, date)
        notes.add(noteLink)
        return noteLink
    }

    override fun getAllNotes(): List<Note> {
        return notes
    }

    override fun getAllTextNotes(): List<Note> {
        val textNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "TextNote") textNotes.add(notes[number])
        return textNotes
    }

    override fun getAllTasks(): List<Note> {
        val taskNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "Task") taskNotes.add(notes[number])
        return taskNotes
    }

    override fun getAllLinks(): List<Note> {
        val linkNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "Link") linkNotes.add(notes[number])
        return linkNotes
    }

    override fun sortByTitle(): List<Note> {
        notes.sortBy { it.title }
        return notes
    }

    override fun sortByDate(): List<Note> {
        notes.sortWith(
            compareBy(
                { it.date.year }, { it.date.month }, { it.date.day }
            )
        )
        notes.reverse()
        return notes
    }

    override fun removeNote(title: String) {
        notes.removeIf { it.title == title }
    }

    override fun findByTypeAndTitle(type: String, title: String): Note {
        var count = 0
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == type && index.title == title) count = number
        return notes[count]
    }

    override fun toString(): String {
        return "Notes:\n" +
                "$notes"
    }


}


fun main() {
    val note1 = ApplicationNotes()
    note1.createTextNote("My Course_Work", "Sealed classes and interfaces represent restricted class hierarchies that provide more control over inheritance.", Date(5, 12, 1993))
    note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
    note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
    note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))

    println(note1.getAllTextNotes())
    println(note1.sortByTitle())
    println(note1.sortByDate())
    println(note1.findByTypeAndTitle("Link", "YouTube"))
    note1.removeNote("Anime")
    println(note1.getAllNotes())
}