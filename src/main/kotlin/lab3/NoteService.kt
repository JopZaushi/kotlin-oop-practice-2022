package lab3

interface NoteService {
    fun getAllNotes(): List<Note>
    fun getAllTextNotes(): List<Note.TextNote>
    fun getAllTasks(): List<Note.Task>
    fun getAllLinks(): List<Note.Link>

    fun createTextNote(title: String, content: String, date: Date): Note.TextNote
    fun createTask(title: String, content: String, deadline: String, date: Date): Note.Task
    fun createLink(title: String, content: String, url: String, date: Date): Note.Link

    fun sortByTitle(): List<Note>
    fun sortByDate(): List<Note>

    fun removeNote(note: Note)

    fun findByTypeAndTitle(type: Class<out Note>, title: String): List<Note>
}