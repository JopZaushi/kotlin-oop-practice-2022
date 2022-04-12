package lab1

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