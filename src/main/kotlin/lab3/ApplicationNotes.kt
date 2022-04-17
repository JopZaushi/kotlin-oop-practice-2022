package lab3

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
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        return notes
    }

    override fun getAllTextNotes(): List<Note> {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        val textNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "TextNote") textNotes.add(notes[number])
        if (textNotes.isEmpty())
            throw Exception("There are no TextNotes")
        return textNotes
    }

    override fun getAllTasks(): List<Note> {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        val taskNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "Task") taskNotes.add(notes[number])
        if (taskNotes.isEmpty())
            throw Exception("There are no Tasks")
        return taskNotes
    }

    override fun getAllLinks(): List<Note> {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        val linkNotes: MutableList<Note> = mutableListOf()
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == "Link") linkNotes.add(notes[number])
        if (linkNotes.isEmpty())
            throw Exception("There are no Links")
        return linkNotes
    }

    override fun sortByTitle(): List<Note> {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        notes.sortBy { it.title }
        return notes
    }

    override fun sortByDate(): List<Note> {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        notes.sortWith(
            compareBy(
                { it.date.year }, { it.date.month }, { it.date.day }
            )
        )
        notes.reverse()
        return notes
    }

    override fun removeNote(title: String) {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        notes.removeIf { it.title == title }
    }

    override fun findByTypeAndTitle(type: String, title: String): Note {
        if (notes.isEmpty())
            throw Exception("There are no Notes")
        var count = 0
        for ((number, index) in notes.withIndex())
            if (index.javaClass.simpleName == type && index.title == title) count = number
        return notes[count]
    }

    override fun toString(): String {
        return "Notes:\n" +
                "$notes"
    }

    operator fun get(index: Int): Note {
        return notes[index]
    }
}