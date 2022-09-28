package lab1
import java.awt.Desktop
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLEncoder

fun main() {
    var flag = false
    var question = ""
    var flagPageid = false
    while (!flag) {
        println("Введите запрос, информацию о котором хотите найти: ")
        var key = readLine()
        if (key != "") {
            key = URLEncoder.encode(key, "UTF-8")
            question = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=$key"
            println(question)
            flag = true
        }
    }


    val connection = URL(question).openConnection() as HttpURLConnection
    val request = connection.inputStream.bufferedReader().readText()
    val listAnswers: List<Answer>? = parserUrlAnswer(request)
    val listRequest: List<String> = printAnswer(listAnswers)
//    println("Results of request: ${listAnswers}")
//    println("Results of request: ${listRequest}")
    listRequest.forEach(::println)

    while (!flagPageid) {
        println("\nВведите номер страницы, которую хотите найти: ")
        val page = readLine()
        if (page != null) {
            if(page.toIntOrNull() != null) {
                val resultPage = "https://ru.wikipedia.org/w/index.php?curid=" + "$page"
                println(resultPage)
                val desk: Desktop = Desktop.getDesktop()
                desk.browse(URI("https://ru.wikipedia.org/w/index.php?curid=" + "$page"))
                flagPageid = true
            }
        }
    }

}
