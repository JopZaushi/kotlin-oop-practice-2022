package lab2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ShapeCollectorTest {

    @Test
    fun addFigure() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        val groupOfFigureExtra = ShapeCollector()
        groupOfFigureExtra.addFigure(shape2)
        groupOfFigureExtra.addFigure(shape1)
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(2, groupOfFigure.getSizeGroup())
        assertEquals(groupOfFigure[0], groupOfFigureExtra[1])
        assertEquals(groupOfFigure[1], groupOfFigureExtra[0])
        assertEquals(listOf(shape1, shape2), groupOfFigure.getGroupOfFigure())
    }

    @Test
    fun minAreaFigureOfGroup() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(shape1, groupOfFigure.minAreaFigureOfGroup())
    }

    @Test
    fun maxAreaFigureOfGroup() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(shape2, groupOfFigure.maxAreaFigureOfGroup())
    }

    @Test
    fun sumAreaFigureOfGroup() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(32.26, groupOfFigure.sumAreaFigureOfGroup())
    }

    @Test
    fun findFigureOfGroupByBorderColor() {
        val color1 = Color("255", "255", "255", "5")
        val color2 = Color("1", "1", "1", "0")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color2, 3.0)
        val shape3 = Square(color2, color1, 1.0)
        val shape4 = Rectangle(color2, color2, 5.0, 4.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        groupOfFigure.addFigure(shape3)
        groupOfFigure.addFigure(shape4)
        assertEquals(listOf(shape3, shape4), groupOfFigure.findFigureOfGroupByBorderColor(color2))
    }

    @Test
    fun findFigureOfGroupByFillColor() {
        val color1 = Color("255", "255", "255", "5")
        val color2 = Color("1", "1", "1", "0")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color2, 3.0)
        val shape3 = Square(color2, color1, 1.0)
        val shape4 = Rectangle(color2, color2, 5.0, 4.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        groupOfFigure.addFigure(shape3)
        groupOfFigure.addFigure(shape4)
        assertEquals(listOf(shape1, shape3), groupOfFigure.findFigureOfGroupByFillColor(color1))
    }

    @Test
    fun getGroupOfFigure() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(listOf(shape1, shape2), groupOfFigure.getGroupOfFigure())
    }

    @Test
    fun getSizeGroup() {
        val color1 = Color("255", "255", "255", "5")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color1, 3.0)
        val groupOfFigure = ShapeCollector()
        val groupOfFigureExtra = ShapeCollector()
        groupOfFigureExtra.addFigure(shape2)
        groupOfFigureExtra.addFigure(shape1)
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        assertEquals(2, groupOfFigure.getSizeGroup())
        assertEquals(2, groupOfFigureExtra.getSizeGroup())
    }

    @Test
    fun figureOfMapBorder() {
        val color1 = Color("255", "255", "255", "5")
        val color2 = Color("1", "1", "1", "0")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color2, 3.0)
        val shape3 = Square(color2, color1, 1.0)
        val shape4 = Rectangle(color2, color2, 5.0, 4.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        groupOfFigure.addFigure(shape3)
        groupOfFigure.addFigure(shape4)
        assertEquals(mapOf(color2 to listOf(shape3, shape4), color1 to listOf(shape1, shape2)),
            groupOfFigure.figureOfMapBorder())
    }

    @Test
    fun figureOfMapFill() {
        val color1 = Color("255", "255", "255", "5")
        val color2 = Color("1", "1", "1", "0")
        val shape1 = Triangle(color1, color1, 4.0, 2.0)
        val shape2 = Circle(color1, color2, 3.0)
        val shape3 = Square(color2, color1, 1.0)
        val shape4 = Rectangle(color2, color2, 5.0, 4.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        groupOfFigure.addFigure(shape3)
        groupOfFigure.addFigure(shape4)
        assertEquals(mapOf(color2 to listOf(shape2, shape4), color1 to listOf(shape1, shape3)),
            groupOfFigure.figureOfMapFill())
    }

    @Test
    fun figureSquare() {
        val color1 = Color("255", "255", "255", "5")
        val color2 = Color("1", "1", "1", "0")
        val shape1 = Square(color1, color2, 4.0)
        val shape2 = Circle(color1, color2, 3.0)
        val shape3 = Square(color2, color1, 1.0)
        val groupOfFigure = ShapeCollector()
        groupOfFigure.addFigure(shape1)
        groupOfFigure.addFigure(shape2)
        groupOfFigure.addFigure(shape3)
        assertEquals(listOf(shape1, shape3), groupOfFigure.figureSquare("Square"))
    }
}