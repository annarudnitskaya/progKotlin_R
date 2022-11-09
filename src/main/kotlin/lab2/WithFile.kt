package lab2


import lab1.Answer
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
//import org.apache.commons.csv.CSVFormat
//import org.apache.commons.csv.CSVParser
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory

class WithFile {
    // XML
    fun readXML(filePath: String):List<Address> {
        val file = File(filePath).readLines()
        val listAddresses = mutableListOf<Address>()
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()

//        val bufferedReader = BufferedReader(FileReader(filePath))
//        if (file.isEmpty()) return null
        var perem: String
        for (i in file.withIndex()) {
            perem = i.toString()
            //println(perem)
//            perem=perem.substringAfter("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<root>").trim()

            perem = perem.substringAfter("<item city=\"").trim()
            val city = perem.substringBefore("\"").trim() //city
            println(city)
            perem = perem.substringAfter("\" street=\"")
            val street = perem.substringBefore("\"").trim() //street

            perem = perem.substringAfter("\" house=\"").trim()
            val house = perem.substringBefore("\"").trim() //house

            perem = perem.substringAfter("\" floor=\"").trim()
            val floorsNum = perem.substringBefore("\"").trim() //floors

            perem = perem.substringAfter("\" />\n").trim()

            val fullInfo = Address(city, street, house, floorsNum)

            listAddresses.add(fullInfo)
        }
        return listAddresses


    }

    // CSV
    fun readCSV(filePath: String):List<Address> {
        val file = File(filePath).readLines()
        val listAddresses = mutableListOf<Address>()
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()

//        val bufferedReader = BufferedReader(FileReader("filePath"))
//        if (file.isEmpty()) return null
        var perem: String
        for ((cout, i) in file.withIndex()) {
            perem = i
            perem=perem.substringAfter("\n").trim()
            perem = perem.substringAfter("\"").trim()
            val city = perem.substringBefore("\"").trim() //city

            perem = perem.substringAfter("\";\"")
            val street = perem.substringBefore("\"").trim() //street

            perem = perem.substringAfter("\";").trim()
            val house = perem.substringBefore(";").trim() //house

            perem = perem.substringAfter(";").trim()
            val floorsNum = perem.substringBefore(";").trim() //floors


            val fullInfo = Address(city, street, house, floorsNum)
            listAddresses.add(fullInfo)
        }
        return listAddresses


    }
}



