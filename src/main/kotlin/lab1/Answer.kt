package lab1

class Answer (
    val ns: String,
    val title: String,
    val pageid: String,
    val size: String,
    val wordcount: String,
    val snippet: String,
    val timestamp: String
)

fun parserURL(request: String): List<Answer>?{
    if (request.isEmpty()) return null
    val splitString = request.split(']')
    val listBooks = mutableListOf<Answer>()
    var perem: String
    if (str in splitString) {
        perem = str
        perem = perem.substringAfter('[')
        perem = perem.substringAfter(':')
        val ns = perem.substringBefore(",").trim() //ns

        perem = perem.substringAfter(":")
        val title = perem.substringBeforeLast(",").trim()
        if (title.isEmpty()) {
            throw IllegalArgumentException("This answer has no title")
        }//title

        perem = perem.substringAfter(":")
        val pageid = perem.substringBeforeLast(",").trim()
        if (pageid.isEmpty()) {
            throw IllegalArgumentException("This answer has no page id, so we can't open it")
        }//pageid

        perem = perem.substringAfter(":")
        val size = perem.substringBeforeLast(",").trim() //size

        perem = perem.substringAfter(":")
        val wordcount = perem.substringBeforeLast(",").trim() //wordcount

        perem = perem.substringAfter(":")
        val snippet = perem.substringBeforeLast(",").trim() //snippet

        perem = perem.substringAfter(":")
        val timestamp = perem.substringBeforeLast(",").trim() //timestamp

        val fullInfo = Answer(ns, title, pageid, size, wordcount, snippet, timestamp)
        listAnswer.add(fullInfo)
    }
    return listAnswer

}