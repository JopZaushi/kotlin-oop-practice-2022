package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class UnitTest1 {
    @Test
    fun testFreshOfYear() {
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        assertEquals(2002, bookFreshByYear(list)[0].year)
    }

    @Test
    fun testEldestOfYear() {
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        assertEquals(1915, bookEldestByYear(list)[0].year)
    }

    @Test
    fun testLongOfYear() {
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        assertEquals("Помощь другим", bookLongestTitle(list)[0].name)
    }

    @Test
    fun testShortOfYear() {
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        val list: List<Book> = parseBooks(string)
        assertEquals("Роботы", bookShortTitle(list)[0].name)
    }

    @Test
    fun testParser() {
        val string = "1.Роботы//Горохов П.А,Зиянгиров Р.Р//1915\n" +
                "2.Круг ада//Понаморёв М.Е//1999\n" +
                "3.Помощь другим//Шелудько Г.В//2002"
        assertEquals("Роботы", parseBooks(string)[0].name)
        assertEquals("Горохов П.А,Зиянгиров Р.Р", parseBooks(string)[0].author)
        assertEquals(1915, parseBooks(string)[0].year)
        assertEquals("Помощь другим", parseBooks(string)[2].name)
        assertEquals("Шелудько Г.В", parseBooks(string)[2].author)
        assertEquals(2002, parseBooks(string)[2].year)
    }
}