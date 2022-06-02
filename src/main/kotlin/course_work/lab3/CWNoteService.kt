package course_work.lab3

import java.time.LocalDate

interface CWNoteService {
    fun getAllNotes(): List<CWNote>
    fun getAllTextNotes(): List<CWNote.TextNote>
    fun getAllTasks(): List<CWNote.Task>
    fun getAllLinks(): List<CWNote.Link>

    fun createTextNote(title: String, content: String, date: LocalDate): CWNote.TextNote
    fun createTask(title: String, content: String, deadline: String, date: LocalDate): CWNote.Task
    fun createLink(title: String, content: String, url: String, date: LocalDate): CWNote.Link

    fun sortByTitle(): List<CWNote>
    fun sortByDate(): List<CWNote>

    fun removeNote(note: CWNote)

    fun findByTypeAndTitle(type: Class<out CWNote>, title: String): List<CWNote>
}