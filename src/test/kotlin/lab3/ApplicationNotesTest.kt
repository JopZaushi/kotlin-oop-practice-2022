package lab3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ApplicationNotesTest {

    @Test
    fun createTextNote() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Ivan", "Ivanov", Date(13, 10, 2021))
        assertEquals(1, note1.getAllNotes().size)
        assertEquals(note1[0].title, "Ivan")
        assertEquals(note1[0].date.day, 13)
        assertEquals(note1[0].date.month, 10)
        assertEquals(note1[0].date.year, 2021)
    }

    @Test
    fun createTask() {
        val note1 = ApplicationNotes()
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        assertEquals(1, note1.getAllNotes().size)
        assertEquals(note1[0].title, "Do a lab on Kotlin")
        assertEquals(note1[0].date.day, 15)
        assertEquals(note1[0].date.month, 4)
        assertEquals(note1[0].date.year, 2022)
    }

    @Test
    fun createLink() {
        val note1 = ApplicationNotes()
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(1, note1.getAllNotes().size)
        assertEquals(note1[0].title, "YouTube")
        assertEquals(note1[0].date.day, 2)
        assertEquals(note1[0].date.month, 12)
        assertEquals(note1[0].date.year, 2021)
    }

    @Test
    fun getAllNotes() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[0], note1[1], note1[2]), note1.getAllNotes())
    }

    @Test
    fun getAllTextNotes() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[0]), note1.getAllTextNotes())
    }

    @Test
    fun getAllTasks() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[1]), note1.getAllTasks())
    }

    @Test
    fun getAllLinks() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[2]), note1.getAllLinks())
    }

    @Test
    fun sortByTitle() {
        val note1 = ApplicationNotes()
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        assertEquals(listOf(note1[2], note1[0], note1[1]), note1.sortByTitle())
    }

    @Test
    fun sortByDate() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[1], note1[2], note1[0]), note1.sortByDate())
    }

    @Test
    fun removeNote() {
        val note1 = ApplicationNotes()
        val removeLink = note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(4, note1.getAllNotes().size)
        note1.removeNote(removeLink)
        assertEquals(listOf(note1[0], note1[1], note1[2]), note1.getAllNotes())
    }

    @Test
    fun findByTypeAndTitle() {
        val note1 = ApplicationNotes()
        note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
        note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))
        note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))
        assertEquals(listOf(note1[1]), note1.findByTypeAndTitle(Note.Task::class.java, "Do a lab on Kotlin"))
    }
}