package lab2

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Triangle(
    override val borderColor: @Contextual Color,
    override val fillColor: @Contextual Color,
    val sideA: Double,
    val sideB: Double,
    val sideC: Double,
) : ColoredShape2d {
    init {
        if ((sideA <= 0) || (sideB <= 0) || (sideC <= 0) || (sideA > sideB + sideC) || (sideB > sideA + sideC) || (sideC > sideA + sideB))
            throw IllegalArgumentException("Invalid Arguments")
    }

    override fun calcArea(): Double {
        val perimeter: Double = (this.sideA + this.sideB + this.sideC) / 2
        return kotlin.math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC))
    }
}
@Serializable
data class Circle(
    override val borderColor: @Contextual Color,
    override val fillColor: @Contextual Color,
    val radius: Double,
) : ColoredShape2d {
    init {
        if (radius <= 0)
            throw IllegalArgumentException("Invalid Arguments")
    }

    override fun calcArea(): Double {
        return 3.14 * radius * radius
    }
}
@Serializable
data class Square(
    override val borderColor: @Contextual Color,
    override val fillColor: @Contextual Color,
    val side: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return side * side
    }
}
@Serializable
data class Rectangle(
    override val borderColor: @Contextual Color,
    override val fillColor: @Contextual Color,
    val width: Double,
    val length: Double,
) : ColoredShape2d {
    override fun calcArea(): Double {
        return width * length
    }
}