package lab3

interface NoteService {
    val notes: MutableList<Note>


    fun getAllNotes(): List<Note>
    fun getAllTextNotes(text: String): List<Note>
    //fun getAllTasks(): List<Note>
    //fun getAllLinks(): List<Note>

    fun createTextNote(title: String, content: String, date: String): Note.TextNote
    fun createTask(title: String, content: String, deadline: String, date: String): Note.Task
    fun createLink(title: String, content: String, url: String, date: String): Note.Link

    fun sortByTitle(): List<Note>

}


sealed class Note(open val title: String, open val date: String) {

    class TextNote(override val title: String, private val content: String, override val date: String) : Note(title, date) {
        override fun toString(): String {
            return "TextNote(title='$title', content='$content', date='$date')"
        }

    }

    class Task(override val title: String, private val content: String, private val deadline: String, override val date: String) : Note(title, date) {
        override fun toString(): String {
            return "Task(title='$title', content='$content', deadline='$deadline', date='$date')"
        }
    }

    class Link(override val title: String, private val content: String, private val url: String, override val date: String) : Note(title, date) {
        override fun toString(): String {
            return "Link(title='$title', content='$content', url='$url', date='$date')"
        }
    }


}

class Redactor : NoteService {
    override val notes: MutableList<Note> = mutableListOf()

    override fun createTextNote(title: String, content: String, date: String): Note.TextNote {
        val noteText = Note.TextNote(title, content, date)
        notes.add(noteText)
        return noteText
    }

    override fun createTask(title: String, content: String, deadline: String, date: String): Note.Task {
        val noteTask = Note.Task(title, content, deadline, date)
        notes.add(noteTask)
        return noteTask
    }

    override fun createLink(title: String, content: String, url: String, date: String): Note.Link {
        val noteLink = Note.Link(title, content, url, date)
        notes.add(noteLink)
        return noteLink
    }

    override fun getAllNotes(): List<Note> {
        return notes
    }

    override fun getAllTextNotes(text: String): List<Note> {
        val textNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == text) textNotes.add(notes[number])
        return textNotes
    }

    override fun sortByTitle(): List<Note> {
        val sortNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.title == "ruslan") sortNotes.add(notes[number])
        return sortNotes
    }

    override fun toString(): String {
        return "Redactor(notes=$notes)"
    }


}


fun main() {
    val note1 = Redactor()
    note1.createTextNote("lets go", "pupa", "01.08.2002")
    note1.createTextNote("ruslan", "papu", "02.08.2002")
    note1.createTask("ruslan", "papu", "02.08.2002", "05.09.2002")
    note1.createLink("ruslan", "papu", "https://www.youtube.com", "05.09.2002")

    println(note1)
    println(note1.getAllTextNotes("TextNote"))
    println(note1.sortByTitle())
}