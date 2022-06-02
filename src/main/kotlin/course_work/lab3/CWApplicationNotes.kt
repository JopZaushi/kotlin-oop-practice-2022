package course_work.lab3

import java.time.LocalDate

class CWApplicationNotes : CWNoteService {

    private val notes: MutableList<CWNote> = mutableListOf()

    override fun createTextNote(title: String, content: String, date: LocalDate): CWNote.TextNote {
        val noteText = CWNote.TextNote(title, content, date)
        notes.add(noteText)
        return noteText
    }

    override fun createTask(title: String, content: String, deadline: String, date: LocalDate): CWNote.Task {
        val noteTask = CWNote.Task(title, content, deadline, date)
        notes.add(noteTask)
        return noteTask
    }

    override fun createLink(title: String, content: String, url: String, date: LocalDate): CWNote.Link {
        val noteLink = CWNote.Link(title, content, url, date)
        notes.add(noteLink)
        return noteLink
    }

    override fun getAllNotes(): List<CWNote> {
        if (notes.isEmpty())
           return emptyList()
        return notes
    }

    override fun getAllTextNotes(): List<CWNote.TextNote> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<CWNote.TextNote>()
    }

    override fun getAllTasks(): List<CWNote.Task> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<CWNote.Task>()
    }

    override fun getAllLinks(): List<CWNote.Link> {
        if (notes.isEmpty())
            return emptyList()
        return notes.filterIsInstance<CWNote.Link>()
    }

    override fun sortByTitle(): List<CWNote> {
        if (notes.isEmpty())
            return emptyList()
        notes.sortBy { it.title }
        return notes
    }

    override fun sortByDate(): List<CWNote> {
        if (notes.isEmpty())
            return emptyList()
        notes.sortWith(
            compareBy(
                { it.date.year }, { it.date.month }, { it.date.dayOfMonth }
            )
        )
        notes.reverse()
        return notes
    }

    override fun removeNote(note: CWNote) {
        if (notes.isEmpty())
            throw IllegalArgumentException("There are no Notes")
        notes.removeIf { it == note}
    }

    override fun findByTypeAndTitle(type: Class<out CWNote>, title: String): List<CWNote> {
        if (notes.isEmpty())
            return emptyList()
        val typeNotes: MutableList<CWNote> = notes.filterIsInstance(type) as MutableList<CWNote>
        return typeNotes.filter { it.title == title }
    }

    override fun toString(): String {
        return "Notes:\n" +
                "$notes"
    }

    operator fun get(index: Int): CWNote {
        return notes[index]
    }
}