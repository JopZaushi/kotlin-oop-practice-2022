package lab2

data class Triangle(
    override val borderColor: Color,
    override val fillColor: Color,
    private val height: Double,
    private val baseSide: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return height * baseSide / 2
    }
}

data class Circle(
    override val borderColor: Color,
    override val fillColor: Color,
    private val radius: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return 3.14 * radius * radius
    }
}

data class Square(
    override val borderColor: Color,
    override val fillColor: Color,
    private val side: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return side * side
    }
}

data class Rectangle(
    override val borderColor: Color,
    override val fillColor: Color,
    private val width: Double,
    private val length: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return width * length
    }
}