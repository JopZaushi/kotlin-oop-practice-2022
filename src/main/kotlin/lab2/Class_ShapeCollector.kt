package lab2

class ShapeCollector {
    private val groupFigure: MutableList<ColoredShape2d> = mutableListOf()

    fun addFigure(figure: ColoredShape2d) {
        groupFigure.add(figure)
    }

    fun minAreaFigureOfGroup(): ColoredShape2d {
        var minArea = Double.MAX_VALUE
        var numElement = 0
        for ((number, index) in groupFigure.withIndex())
            if (index.calcArea() > minArea) {
                minArea = index.calcArea()
                numElement = number
            }
        return groupFigure[numElement]
    }

    fun maxAreaFigureOfGroup(): ColoredShape2d {
        var maxArea = Double.MIN_VALUE
        var numElement = 0
        for ((number, index) in groupFigure.withIndex())
            if (index.calcArea() > maxArea) {
                maxArea = index.calcArea()
                numElement = number
            }
        return groupFigure[numElement]
    }

    fun sumAreaFigureOfGroup(): Double {
        var sum = 0.0
        for (index in groupFigure)
            sum += index.calcArea()
        return sum
    }

    fun findFigureOfGroupByBorderColor(_borderColor: Color): List<ColoredShape2d> {
        val groupFigureBorder: MutableList<ColoredShape2d> = mutableListOf()
        for ((number, index) in groupFigure.withIndex())
            if (index.borderColor == _borderColor) groupFigureBorder.add(groupFigure[number])
        return groupFigureBorder
    }

    fun findFigureOfGroupByFillColor(_fillColor: Color): List<ColoredShape2d> {
        val groupFigureFill: MutableList<ColoredShape2d> = mutableListOf()
        for ((number, index) in groupFigure.withIndex())
            if (index.fillColor == _fillColor) groupFigureFill.add(groupFigure[number])
        return groupFigureFill
    }

    fun getGroupOfFigure(): List<ColoredShape2d> {
        return groupFigure
    }

    fun getSizeGroup(): Int {
        return groupFigure.size
    }

    fun figureOfMapBorder(): Map<Color, List<ColoredShape2d>> {
        return groupFigure.groupBy { it.borderColor }
    }

    fun figureOfMapFill(): Map<Color, List<ColoredShape2d>> {
        return groupFigure.groupBy { it.fillColor }
    }

    fun figureSquare(shape: String): List<ColoredShape2d> {
        val groupFigureShape: MutableList<ColoredShape2d> = mutableListOf()
        for ((number, index) in groupFigure.withIndex())
            if (index.javaClass.simpleName == shape) groupFigureShape.add(groupFigure[number])
        return groupFigureShape
    }

    operator fun get(index: Int): ColoredShape2d {
        return groupFigure[index]
    }

}
