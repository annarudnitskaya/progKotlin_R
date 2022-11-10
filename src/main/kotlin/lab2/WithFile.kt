package lab2

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.NamedNodeMap
import lab1.Answer
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import org.w3c.dom.Document
import javax.xml.parsers.DocumentBuilderFactory


class WithFile {
    // XML
    fun readXML(filePath: String):List<Address> {
        val listAddresses = mutableListOf<Address>()
//        val startTime = System.currentTimeMillis()
        val map: HashMap<String, Int> = HashMap()
        // Получение фабрики, чтобы после получить билдер документов
        val factory = DocumentBuilderFactory.newInstance()
        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева
        val builder: DocumentBuilder = factory.newDocumentBuilder()
        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно
        val document: Document = builder.parse(File(filePath))
        // Получение списка всех элементов item внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла)
        val item = document.getElementsByTagName("item")
        for (i in 0 until item.length) {
            // Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            val attributes: NamedNodeMap = item.item(i).attributes
            val city: String = attributes.getNamedItem("city").nodeValue
            val street: String = attributes.getNamedItem("street").nodeValue
            val house: String = attributes.getNamedItem("house").nodeValue
            val floorsNum: String = attributes.getNamedItem("floor").nodeValue
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



