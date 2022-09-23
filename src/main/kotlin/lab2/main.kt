package lab2

fun main() {
    val color1 = Color(255, 255, 255, 5)
    val color2 = Color(1, 1, 1, 0)

    val shape1 = Triangle(color1, color1, 3.0, 2.0, 5.0)
    val shape2 = Circle(color1, color2, 3.0)
    val shape3 = Square(color2, color1, 1.0)
    val groupOfFigure = ShapeCollector()

    groupOfFigure.addFigure(shape1)
    groupOfFigure.addFigure(shape2)
    groupOfFigure.addFigure(shape3)

    println("Фигура, у которой минимальная площадь:")
    println(groupOfFigure.minAreaFigureOfGroup())
    println("Фигура, у которой максимальная площадь:")
    println(groupOfFigure.maxAreaFigureOfGroup())
    println("Сумма площадей всех фигур:")
    println(groupOfFigure.sumAreaFigureOfGroup())
    println("Фигуры с одинаковой обводкой:")
    println(groupOfFigure.findFigureOfGroupByBorderColor(color2))
    println("Фигуры с одинаковой заливкой")
    println(groupOfFigure.findFigureOfGroupByFillColor(color1))
    println("Все фигуры:")
    println(groupOfFigure.getGroupOfFigure())
    println("Количество фигур:")
    println(groupOfFigure.getSizeGroup())
    println("Фигуры с одинаковой заливкой")
    println(groupOfFigure.figureOfMapFill())
    println("Фигуры с одинаковой обводкой:")
    println(groupOfFigure.figureOfMapBorder())
    println("Вывести конкретные фигуры:")
    println(groupOfFigure.figureGroupOfType(Square::class.java))
}
