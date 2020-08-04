package com.example.algorithm

fun main() {
    var a = arrayOfNulls<Int>(5)

    a[1] = 37
    a[2] = 51
    a[4] = a[1]?.times(2)

    for(i in 0 until a.size) {
        println("a[" + i + "] = " + a[i])
    }
    IntArrayInit()
}

fun IntArrayInit() {
    var a = arrayOf(1, 2, 3, 4, 5)

    for (i in 0 until a.size) {
        println("a[" + i + "] = " + a[i])
    }
}