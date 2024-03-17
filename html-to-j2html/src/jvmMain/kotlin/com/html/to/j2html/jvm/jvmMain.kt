package com.html.to.j2html.jvm

import com.html.to.j2html.common.convert
import java.io.File

fun main() {
    val inputFile = File("input.html")
    if(inputFile.exists()){
        println("input path : ${inputFile.absolutePath}")
        val outputFile = File("output.java")
        val output = convert(inputFile.readText())
        outputFile.writeText(output)
        println("converted to ${outputFile.absolutePath}")
    }
    else {
        println("input.html not found")
    }

}

