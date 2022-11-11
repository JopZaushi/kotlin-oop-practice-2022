package lab2_5sem

import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

class Item(
    private val city: String,
    private val street: String,
    private val house: String,
    private val floor: String,
) {
    fun getString(): String = "$city $street $house $floor"
    fun getCityFloor(): String = "$city $floor"
}

fun parseXML(file: String) {
    val startTime = System.currentTimeMillis()
    //record keeping
    val items = mutableMapOf<String, Int>()
    val repeatString = mutableMapOf<String, Int>()
    val countFloor = mutableMapOf<String, Int>()


    //Getting a factory to get the document builder after
    val factory = DocumentBuilderFactory.newInstance()

    //We got a builder from the factory that parses XML, creates the Document structure in the form of a hierarchical tree
    val builder = factory.newDocumentBuilder()

    //We parsed XML by creating a Document structure. Now we have access to all the elements we need
    val document: Document? = builder.parse(File(file))

    //Getting a list of all employee elements inside the root element
    val itemElements: NodeList = document!!.documentElement.getElementsByTagName("item")

    //Iterating over lines
    for (i in 0 until itemElements.length) {
        val item: Node = itemElements.item(i)
        //Getting the attributes of each element
        val attributes: NamedNodeMap = item.attributes

        val addString = Item(attributes.getNamedItem("city").nodeValue,
            attributes.getNamedItem("street").nodeValue,
            attributes.getNamedItem("house").nodeValue,
            attributes.getNamedItem("floor").nodeValue).getString()

        val addFloor = Item(attributes.getNamedItem("city").nodeValue,
            attributes.getNamedItem("street").nodeValue,
            attributes.getNamedItem("house").nodeValue,
            attributes.getNamedItem("floor").nodeValue).getCityFloor()

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