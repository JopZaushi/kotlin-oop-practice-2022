package lab1

fun parseBooks(books: String): List<Book> {
    var string = 0 //amount strings
    var counter = 0
    var nameBook: String
    var author: String
    var year: Int
    var changeBook: String = books
    val listOfBooks: MutableList<Book> = mutableListOf()
    //line counting
    for (index in 0 until books.length - 1) if (books[index].isDigit() && books[index + 1] == '.') string++
    //entering fields into a list
    for (index in 1..string) {
        changeBook = changeBook.drop(2)

        while (changeBook[counter] != '/') counter++
        nameBook = changeBook.substring(0, counter)
        changeBook = changeBook.drop(counter + 2)
        counter = 0

        while (changeBook[counter] != '/') counter++
        author = changeBook.substring(0, counter)
        changeBook = changeBook.drop(counter + 2)
        counter = 0

        if (index != string) while (changeBook[counter] != '\n') counter++
        else counter = changeBook.length

        year = changeBook.substring(0, counter).toInt()
        changeBook = changeBook.drop(counter + 1)
        counter = 0

        val book = Book(nameBook, author, year) // create object
        listOfBooks.add(book)
    }
    return listOfBooks
}

fun bookFreshByYear(list: List<Book>): List<Book> {
    var freshBook = Int.MIN_VALUE
    for (index in list)
        if (index.year > freshBook) freshBook = index.year
    return list.filter { it.year == freshBook }
}

fun bookEldestByYear(list: List<Book>): List<Book> {
    var eldestBook = Int.MAX_VALUE
    for (index in list) if (index.year < eldestBook) eldestBook = index.year
    return list.filter { it.year == eldestBook }
}

fun bookLongestTitle(list: List<Book>): List<Book> {
    var longNameInt = Int.MIN_VALUE
    var longTitle: String = list.toString()
    for (index in list) {
        if (index.name.length > longNameInt) {
            longNameInt = index.name.length
            longTitle = index.name
        }
    }
    return list.filter { it.name == longTitle }
}

fun bookShortTitle(list: List<Book>): List<Book> {
    var shortNameInt = Int.MAX_VALUE
    var shortTitle: String = list.toString()
    for (index in list) {
        if (index.name.length < shortNameInt) {
            shortNameInt = index.name.length
            shortTitle = index.name
        }
    }
    return list.filter { it.name == shortTitle }
}

fun main() {
    val string: String = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
            "2.Круг ада//Понаморёв М.Е//1999\n" +
            "3.Помощь другим//Шелудько Г.В//2002"
    val list: List<Book> = parseBooks(string)
    println(bookFreshByYear(list))
    println(bookEldestByYear(list))
    println(bookLongestTitle(list))
    println(bookShortTitle(list))
}