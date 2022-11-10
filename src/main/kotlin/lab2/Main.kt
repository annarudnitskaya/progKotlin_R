package lab2

import lab1.printAnswer
import java.io.File

fun main() {
                do {
                println("Enter path to file or F for exit")
                val filePath = readLine()
                val reader = WithFile()
                if (filePath != null && File(filePath).exists()) {
                    if (filePath.lowercase().endsWith("csv")) {
                        reader.readCSV(filePath)
                    } else if (filePath.lowercase().endsWith("xml")) {
                        reader.readXML(filePath)
                    } else println("\nTry again...\n")
                }
            } while (filePath!="f")

}

