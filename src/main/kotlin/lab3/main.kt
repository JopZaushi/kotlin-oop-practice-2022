package lab3

fun main() {
    val note1 = ApplicationNotes()
    note1.createTextNote("My Course_Work", "Sealed classes and interfaces", Date(5, 12, 1993))
    note1.createTextNote("Anime", "Tokyo Ghoul, Attack of Titan, Naruto", Date(13, 10, 2021))
    note1.createTask("Do a lab on Kotlin", "3 Lab", "20.04.2022", Date(15, 4, 2022))

    val removeLink = note1.createLink("YouTube", "The Best", "https://www.youtube.com", Date(2, 12, 2021))

    println(note1.getAllTextNotes())
    println(note1.getAllLinks())
    println(note1.sortByTitle())
    println(note1.sortByDate())
    println(note1.findByTypeAndTitle(Note.Link::class.java, "YouTube"))
    note1.removeNote(removeLink)
    println(note1.getAllNotes())
}
