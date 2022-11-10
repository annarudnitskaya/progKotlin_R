package lab2


import lab1.Answer
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory


class WithFile {
    // XML
    fun readXML(filePath: String):List<Address> {
        val file = File(filePath).readText()
        val listAddresses = mutableListOf<Address>()
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()
      //  val file1 = File("addressBook.txt")
//        val bufferedReader = BufferedReader(FileReader(filePath))
//        if (file.isEmpty()) return null
        val splitString = file.split('\n')
        var perem: String
//        for (i in file.withIndex()) {
        for (str in splitString) {
            perem = str
            perem = perem.substringAfter("<?xml version=\"1.0\" encoding=\"utf-8\"?>").trim()
            perem = perem.substringAfter("\n").trim()
            perem = perem.substringAfter("<root>").trim()
            perem = perem.substringAfter("\n").trim()
            perem = perem.substringAfter("</root>").trim()
            perem = perem.substringAfter("\n").trim()
//            perem=perem.substringAfter("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<root>").trim()
//                perem = perem.substringAfter("<root>\n").trim()
                perem = perem.substringAfter("<item city=\"").trim()
                val city = perem.substringBefore("\"").trim() //city
                //   println(city)
                perem = perem.substringAfter("\" street=\"")
                val street = perem.substringBefore("\"").trim() //street

                perem = perem.substringAfter("\" house=\"").trim()
                val house = perem.substringBefore("\"").trim() //house

                perem = perem.substringAfter("\" floor=\"").trim()
                val floorsNum = perem.substringBefore("\"").trim() //floors


                println(city + " " + street + " " + house + " " +  floorsNum)
                val fullInfo = Address(city, street, house, floorsNum)
                File("addressBook.txt").appendText( city + " " + street + " " + house + " " +  floorsNum + "\n")
                listAddresses.add(fullInfo)
            }

        return listAddresses
    }

    // CSV
    fun readCSV(filePath: String):List<Address> {
 //       val file = File(filePath).readLines()
        val file = File(filePath).readText()

        val listAddresses = mutableListOf<Address>()
        val startTime = System.currentTimeMillis()
        val hashMap: HashMap<Address, Int> = HashMap()
      //  val file1 = File("addressBook.txt")
//        val bufferedReader = BufferedReader(FileReader("filePath"))
//        if (file.isEmpty()) return null
        val splitString = file.split('\n')
        var perem: String
        for (str in splitString) {
                perem = str
                perem = perem.substringAfter("\"city\";\"street\";\"house\";\"floor\"").trim()

                perem = perem.substringAfter("\n").trim()

                perem = perem.substringAfter("\"").trim()
                val city = perem.substringBefore("\"").trim() //city

                perem = perem.substringAfter("\";\"")
                val street = perem.substringBefore("\"").trim() //street

                perem = perem.substringAfter("\";").trim()
                val house = perem.substringBefore(";").trim() //house

                perem = perem.substringAfter(";").trim()
                val floorsNum = perem.substringBefore(";").trim() //floors
                println(city + " " + street + " " + house + " " +  floorsNum)
                val fullInfo = Address(city, street, house, floorsNum)
                listAddresses.add(fullInfo)
                File("addressBook.txt").appendText( city + " " + street + " " + house + " " +  floorsNum + "\n")
            }
        return listAddresses


    }
}



