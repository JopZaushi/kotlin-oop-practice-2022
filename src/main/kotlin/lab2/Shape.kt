package lab2

data class Triangle(
    override val borderColor: Color,
    override val fillColor: Color,
    val sideA: Double,
    val sideB: Double,
    val sideC: Double,
) : ColoredShape2d {
    init {
        if ((sideA <= 0) || (sideB <= 0) || (sideC <= 0) || (sideA > sideB + sideC) || (sideB > sideA + sideC) || (sideC > sideA + sideB))
            throw Exception("Invalid Arguments")
    }

    override fun calcArea(): Double {
        val perimeter: Double = (this.sideA + this.sideB + this.sideC) / 2
        return kotlin.math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC))
    }
}

data class Circle(
    override val borderColor: Color,
    override val fillColor: Color,
    val radius: Double,
) : ColoredShape2d {
    init {
        if (radius <= 0)
            throw Exception("Invalid Arguments")
    }

    override fun calcArea(): Double {
        return 3.14 * radius * radius
    }
}

data class Square(
    override val borderColor: Color,
    override val fillColor: Color,
    val side: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return side * side
    }
}

data class Rectangle(
    override val borderColor: Color,
    override val fillColor: Color,
    val width: Double,
    val length: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return width * length
    }
}