package lab2

import lab1.printAnswer
import java.io.File

fun main() {
    //ввод адреса файла или комбинации на выход

    val nameFile = "address.xml"
    val reader = WithFile()
    reader.readXML(nameFile)

}

