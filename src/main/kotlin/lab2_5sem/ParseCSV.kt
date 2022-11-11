package lab2_5sem

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.nio.file.Files
import java.nio.file.Paths

fun parseCSV(file: String) {
    val startTime = System.currentTimeMillis()
    //record keeping
    val items = mutableMapOf<String, Int>()
    val repeatString = mutableMapOf<String, Int>()
    val countFloor = mutableMapOf<String, Int>()

    // read the file
    val reader = Files.newBufferedReader(Paths.get(file))
    // parse the file into csv values
    val csvParser = CSVParser(reader, CSVFormat.DEFAULT
        .withHeader("city", "street", "house", "floor")
        .withIgnoreHeaderCase()
        .withTrim()
        .withDelimiter(';'))

    for (csvRecord in csvParser) {
        // Accessing Values by Column Index
        val city = csvRecord.get("city")
        val street = csvRecord.get("street")
        val house = csvRecord.get("house")
        val floor = csvRecord.get("floor")

        val addString = "$city $street $house $floor"
        val addFloor = "$city $floor"
        if (addFloor == "city floor")
            continue
        //search for duplicates
        if (!items.contains(addString)) {
            items += addString to 1
        } else {
            var count = 1
            if (!repeatString.contains(addString)) {
                count++
                repeatString += addString to count
            } else {
                count = repeatString[addString]!!
                count++
                repeatString[addString] = count
            }
        }
        //counting of storey buildings
        var count = 0
        if (!countFloor.contains(addFloor)) {
            count++
            countFloor += addFloor to count
        } else {
            count = countFloor[addFloor]!!
            count++
            countFloor[addFloor] = count
        }
    }

    for ((item, count) in repeatString) {
        println("Запись: $item, повторяется столько раз: $count")
    }
    for ((item, count) in countFloor) {
        println("$item-этажных домов: $count")
    }
    val totalTime = System.currentTimeMillis() - startTime
    println("Время работы программы: $totalTime мс")
}