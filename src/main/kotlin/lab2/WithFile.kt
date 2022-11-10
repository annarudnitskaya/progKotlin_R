package lab2

import org.w3c.dom.*
import java.nio.file.Files
import java.nio.file.Paths
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import java.io.File
import java.io.FileReader
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import java.io.BufferedReader
import java.io.InputStream


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
        getStatistics(createHashMap2(listAddresses), createHashMap2(listAddresses))
        return listAddresses
    }

    // CSV
    fun readCSV(filePath: String):List<Address> {
 //       val file = File(filePath).readLines()
//       val file = File(filePath).readText()

            var listAddresses = mutableListOf<Address>()
//        val startTime = System.currentTimeMillis()
//        val hashMap: HashMap<Address, Int> = HashMap()
//        val InStr = file.inputStream()
        val reader = BufferedReader(FileReader(filePath))
        val header = reader.readLine()
        listAddresses = reader.lineSequence()
            .filter { it.isNotBlank() }
            .map {
                val (city, street, house, floorsNum) = it.split(';', ignoreCase = false, limit = 4)
                Address(city.trim().removeSurrounding("\""), street.trim().removeSurrounding("\""), house.trim(), floorsNum.trim())
            }.toList().toMutableList()
        getStatistics(createHashMap2(listAddresses), createHashMap2(listAddresses))
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

        fun getStatistics(map: HashMap<Address, Int>, map2: HashMap<Address, Int>){
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
                        println("In the city of ${i.key.city} there are $amount buildings with a height of $help floors")
                    }
                    listOfCity.add(i.key.city)
                }
                flag = true
            }
        }
}




