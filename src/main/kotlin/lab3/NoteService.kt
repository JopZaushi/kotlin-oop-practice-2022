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