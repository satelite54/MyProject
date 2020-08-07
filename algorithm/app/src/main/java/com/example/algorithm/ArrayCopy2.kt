package com.example.algorithm

fun main () {
    var a = IntArray(5)
    var b = IntArray(5)

    for(i in a.indices) {
        a[i] = 1 + i
    }

    for(j in b.indices) {
        b[j] = 2 + j
    }
    ArrayCopy(a,b)

    for(printArray in 0 until a.size) {
        print("a[$printArray] = ${a[printArray]} ")
    }
}

fun ArrayCopy(a : IntArray, b : IntArray) {
    var ArraySize = if(a.size <= b.size) a.size else b.size
    for(i in 0 until ArraySize) {
        a[i] = b[i]
    }
}