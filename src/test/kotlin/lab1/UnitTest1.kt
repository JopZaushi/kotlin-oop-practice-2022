package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UnitTest1 {
    @Test
    fun testFreshOfYear() {
        var freshBook = Int.MIN_VALUE
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        for (index in list) if (index._year > freshBook) freshBook = index._year
        assertEquals(2002, freshBook)
    }

    @Test
    fun testEldestOfYear() {
        var eldestBook = Int.MAX_VALUE
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        for (index in list) if (index._year < eldestBook) eldestBook = index._year
        assertEquals(1915, eldestBook)
    }

    @Test
    fun testLongOfYear() {
        var longNameInt = Int.MIN_VALUE
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        var longTitle: String = list.toString()
        for (index in list) {
            if (index._name.length > longNameInt) {
                longNameInt = index._name.length
                longTitle = index._name
            }
        }
        assertEquals("Помощь другим", longTitle)
    }

    @Test
    fun testShortOfYear() {
        var shortNameInt = Int.MAX_VALUE
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        var shortTitle: String = list.toString()
        for (index in list) {
            if (index._name.length < shortNameInt) {
                shortNameInt = index._name.length
                shortTitle = index._name
            }
        }
        assertEquals("Роботы", shortTitle)
    }
}