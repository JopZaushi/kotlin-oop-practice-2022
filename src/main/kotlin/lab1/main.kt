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

fun bookFreshByYear(list: List<Book>): Int {
    var freshBook = Int.MIN_VALUE
    for (index in list) if (index._year > freshBook) freshBook = index._year
    return freshBook
}

fun bookEldestByYear(list: List<Book>): Int {
    var eldestBook = Int.MAX_VALUE
    for (index in list) if (index._year < eldestBook) eldestBook = index._year
    return eldestBook
}

fun bookLongestTitle(list: List<Book>): String {
    var longNameInt = Int.MIN_VALUE
    var longTitle: String = list.toString()
    for (index in list) {
        if (index._name.length > longNameInt) {
            longNameInt = index._name.length
            longTitle = index._name
        }
    }
    return longTitle
}

fun bookShortTitle(list: List<Book>): String {
    var shortNameInt = Int.MAX_VALUE
    var shortTitle: String = list.toString()
    for (index in list) {
        if (index._name.length < shortNameInt) {
            shortNameInt = index._name.length
            shortTitle = index._name
        }
    }
    return shortTitle
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