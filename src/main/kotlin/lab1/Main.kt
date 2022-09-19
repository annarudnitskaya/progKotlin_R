import lab1.Answer

fun main(args: Array<String>) {
    println("Hello World!")
    val request = readLine()
    val listAnswers: List<Answer>? = parserUrl(request)
}

