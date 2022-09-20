package lab1

data class Answer (
    val ns: String,
    val title: String,
    val pageid: String,
    val size: String,
    val wordcount: String,
    val snippet: String,
    val timestamp: String
)

fun parserUrlAnswer(request: String): List<Answer>?{
    if (request.isEmpty()) return null
    val splitString = request.split("},{")
    val listAnswers = mutableListOf<Answer>()
    var perem: String
    for (str in splitString) {
        perem = str
        perem = perem.substringAfter('{')
        perem = perem.substringAfter(':')
        perem = perem.substringAfter('[').trim()
        //println(perem)
        val ns = perem.substringBefore(",").trim() //ns

        perem = perem.substringAfter(":\"")
        val title = perem.substringBefore("\",\"").trim()
        if (title.isEmpty()) {
            throw IllegalArgumentException("This answer has no title")
        }//title

        //perem = perem.substringAfter("\":").trim()
        perem = perem.substringAfter("\":")
        //println(perem)
        val pageid = perem.substringBefore(",").trim()
        if (pageid.isEmpty()) {
            throw IllegalArgumentException("This answer has no page id, so we can't open it")
        }//pageid

        perem = perem.substringAfter("\":")
        val size = perem.substringBefore(",").trim() //size

        perem = perem.substringAfter("\":")
        val wordcount = perem.substringBefore(",").trim() //wordcount

        perem = perem.substringAfter("\":")
        val snippet = perem.substringBefore(",").trim() //snippet

        perem = perem.substringAfter("\":\"")
        val timestamp = perem.substringBefore("\"").trim() //timestamp

        val fullInfo = Answer(ns, title, pageid, size, wordcount, snippet, timestamp)
        listAnswers.add(fullInfo)
    }
    return listAnswers
}