package lab1
import java.awt.Desktop
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.net.URLEncoder

fun main() {
    println("Введите запрос, информацию о котором хотите найти: ")
    var key = readLine()
    key = URLEncoder.encode(key, "UTF-8")
    var question = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=$key"
    println(question)

    val connection = URL(question).openConnection() as HttpURLConnection
    val request = connection.inputStream.bufferedReader().readText()
    val listAnswers: List<Answer>? = parserUrlAnswer(request)
    println("Results of request: ${listAnswers}")
//
    println("Введите номер страницы, которую хотите найти: ")
    val page = readLine()
    val resultPage = "https://ru.wikipedia.org/w/index.php?curid="+"$page"
    println(resultPage)
    val desk: Desktop = Desktop.getDesktop()
    desk.browse(URI("https://ru.wikipedia.org/w/index.php?curid="+"$page"))
}
