package lab1_5sem

import java.awt.Desktop
import java.net.URI
import java.net.URL
import java.net.URLEncoder

fun parser() {
    val list: MutableList<Search> = mutableListOf()
    var counter = 0
    var title: String
    var id: String

    //create a link in the format JSON
    println("What are we looking for??")
    var name = readln()
    name = URLEncoder.encode(name, "UTF-8")
    var link =
        URL("https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=$name").readText()

    //determine the number of articles
    while (link[counter] != '"' || link[counter + 1] != 't' || link[counter + 2] != 'o' || link[counter + 3] != 't') counter++
    counter += 12
    val extraIndex: Int = counter

    while (link[counter] != '}') counter++
    val amount: String = link.substring(extraIndex, counter)
    counter = 0

    val value: Int = if (amount.toInt() >= 10) 10 else amount.toInt()

    //parser
    if (value == 0) println("Sorry, it doesn't exist :((((") else {
        for (index in 1..value) {
            //search title
            while (link[counter] != '"' || link[counter + 1] != 't' || link[counter + 2] != 'i' || link[counter + 3] != 't') {
                counter++
            }
            counter += 8
            link = link.drop(counter + 1)
            counter = 0
            //writing name to variable
            while (link[counter] != '"') counter++
            title = link.substring(0, counter)
            counter += 10
            link = link.drop(counter + 1)
            counter = 0
            //writing id
            while (link[counter] != ',') counter++
            id = link.substring(0, counter)
            counter++
            link = link.drop(counter + 1)
            counter = 0
            //populating the "Search" list with variables
            val article = Search(title, id) // create object
            list.add(article)
        }
        //article output and selection
        for (index in 0 until list.size) {
            val nameArticle = list[index].title
            println("$index $nameArticle")
        }
        println("Choose what interests you the most")
        var number = readln()
        while (number.toInt() >= list.size) {
            println("Repeat, please)))")
            number = readln()
        }
        val article = list[number.toInt()].pageid
        //browser opening
        Desktop.getDesktop().browse(URI.create("https://ru.wikipedia.org/w/index.php?curid=$article"))
    }
}





