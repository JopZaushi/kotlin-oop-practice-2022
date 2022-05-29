package course_work.lab3

class ApplicationNotes : NoteService {

    private val notes: MutableList<Note> = mutableListOf()

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
           return emptyList()
        return notes
    }

    override fun getAllTextNotes(): List<Note.TextNote> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<Note.TextNote>()
    }

    override fun getAllTasks(): List<Note.Task> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<Note.Task>()
    }

    override fun getAllLinks(): List<Note.Link> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<Note.Link>()
    }

    override fun sortByTitle(): List<Note> {
        if (notes.isEmpty())
            return emptyList()
        notes.sortBy { it.title }
        return notes
    }

    override fun sortByDate(): List<Note> {
        if (notes.isEmpty())
            return emptyList()
        notes.sortWith(
            compareBy(
                { it.date.year }, { it.date.month }, { it.date.day }
            )
        )
        notes.reverse()
        return notes
    }

    override fun removeNote(note: Note) {
        if (notes.isEmpty())
            throw IllegalArgumentException("There are no Notes")
        notes.removeIf { it == note}
    }

    override fun findByTypeAndTitle(type: Class<out Note>, title: String): List<Note> {
        if (notes.isEmpty())
            return emptyList()
        val typeNotes: MutableList<Note> = notes.filterIsInstance(type) as MutableList<Note>
        return typeNotes.filter { it.title == title }
    }

    override fun toString(): String {
        return "Notes:\n" +
                "$notes"
    }

    operator fun get(index: Int): Note {
        return notes[index]
    }
}