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
//        val file = File(filePath).readText()
        val file = File(filePath).readLines()
        val listAddresses = mutableListOf<Address>()
        val startTime = System.currentTimeMillis()
        val map: HashMap<String, Int> = HashMap()
      //  val file1 = File("addressBook.txt")
//        val bufferedReader = BufferedReader(FileReader(filePath))
//        if (file.isEmpty()) return null
//        val splitString = file.split('\n')
        var perem: String
        for (i in file.withIndex()) {
//        for (str in splitString) {
            perem = i.toString()
            perem = perem.substringAfter("<?xml version=\"1.0\" encoding=\"utf-8\"?> )").trim()
//            perem = perem.substringAfter("\n").trim()
            perem = perem.substringAfter("<root> )").trim()
//            perem = perem.substringAfter("\n").trim()
//            perem = perem.substringAfter("</root>").trim()
//            perem = perem.substringAfter("\n").trim()
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


//                println(city + " " + street + " " + house + " " +  floorsNum)
                val fullInfo = Address(city, street, house, floorsNum)
                val info = city + " " + street + " " + house + " " + floorsNum
                createHashMap(info, map)
//                File("addressBook.txt").appendText( city + " " + street + " " + house + " " +  floorsNum + "\n")
                listAddresses.add(fullInfo)
            }
        getStatistics(map, createHashMap2(listAddresses))

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

    fun createHashMap2(list: List<Address>): HashMap<Address, Int> {
        var map: HashMap<Address, Int> = HashMap()
        for (i in list){
            if (map.containsKey(i)) {
                map[i] = map[i]!! + 1
            } else map[i] = 1
        }
        return map
    }

        fun createHashMap(address: String, map: HashMap<String, Int>) {
//            println(address)
            if (map.containsKey(address)) {
                map[address] = map[address]!! + 1
            } else map[address] = 1
//            println("${map[address]}")
        }

        fun getStatistics(map: HashMap<String, Int>, map2: HashMap<Address, Int>){
            for (it in map){
                if (it.value > 1)
                    println("Address ${it.key} repeated ${it.value} times")
            }
            val listOfCity = mutableListOf<String>()
            var flag = true
            for (i in map2){
                for (temp in listOfCity){
                    if(i.key.city == temp) flag = false
                }
                if (flag){
                    val counter = map2.filterKeys { i.key.city == it.city }
                    for (help in 1..5){
                        val amount = counter.filterKeys { help == it.floorsNum.toInt()}.count()
                        println("In the city of ${i.key.city} there are $amount buildings with a height of ${i.key.floorsNum} floors")
                    }
                    listOfCity.add(i.key.city)
                }
                flag = true
            }
        }
}



