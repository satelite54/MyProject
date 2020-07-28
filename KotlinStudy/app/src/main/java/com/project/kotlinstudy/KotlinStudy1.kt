package com.project.kotlinstudy

fun main() {
    var nA: Int = 10
    var nB: Int? = null

    var nValue : Int = 1234
    var LValue : Long = 1234L
    var nValueByHex : Int = 0x1af
    var nValueByBoolean : Int = 0b11100111


    val strValue = "one line string test"

    val strMultiLineValue = """multiline
|   string @@ /|
|   test"""
    println(nA)
    println(nA)

    println(nValue)
    println(LValue)
    println(nValueByHex)
    println(nValueByBoolean)

    println(strValue)
    println(strMultiLineValue)
}