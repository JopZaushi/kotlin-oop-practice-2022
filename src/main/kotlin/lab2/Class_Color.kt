package lab2

//Shape colors and transparency
data class Color(
    val red: Int,
    val green: Int,
    val blue: Int,
    val transparency: Int,
) {
    init {
        if (this.red !in 0..255 || (this.green !in 0..255) || (this.blue !in 0..255) || (this.transparency !in 0..255))
            throw IllegalArgumentException("Invalid Arguments")
    }
}
