package course_work

import course_work.lab3.CWApplicationNotes
import course_work.lab3.CWNote
import java.time.LocalDate

fun main() {
    val note1 = CWApplicationNotes()
    val date = LocalDate.now()
    note1.createTextNote("My Course_Work", "Sealed classes and interfaces", date)
    note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", date)
    note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", date)

    //val removeLink = note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))

    println(note1.getAllTextNotes())
    println(note1.getAllLinks())
    println(note1.sortByTitle())
    println(note1.sortByDate())
    println(note1.findByTypeAndTitle(CWNote.Link::class.java, "YouTube"))
    //note1.removeNote(removeLink)
    println(note1.getAllNotes())
}
