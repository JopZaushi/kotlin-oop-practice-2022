package lab2_5sem

import java.io.File
import kotlin.system.exitProcess

fun main() {
    var file: String? = null
    while (file != ".end") {
        println("Enter file path")
        file = readln()
        if (file.length > 3 && File(file).exists()) {
            when (file[file.length - 4].toString() + file[file.length - 3].toString() + file[file.length - 2].toString() + file[file.length - 1].toString()) {
                ".csv" -> parseCSV(file)
                ".xml" -> parseXML(file)
                ".end" -> exitProcess(0)
                else -> print("Incorrect input")
            }
            println("\n")
        }
        else println("Incorrect input")
    }
}