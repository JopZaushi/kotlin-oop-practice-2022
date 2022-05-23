package lab6

import lab2.Color
import lab2.Triangle

fun main() {
    val color1 = Color(255, 255, 255, 5)
    val shape1 = Triangle(color1, color1, 3.0, 2.0, 5.0)
    val shape = Serialization()
    val list = shape.decode(shape.read("src/main/kotlin/lab6/read.txt"))
    list.add(shape1)
    shape.write(shape.encode(list), "src/main/kotlin/lab6/write.txt")
}