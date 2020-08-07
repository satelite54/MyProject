package com.example.algorithm

fun main () {
    var a = IntArray(5)
    var b = IntArray(5)

    for(i in a.indices) {
        a[i] = 1
    }

    for(j in b.indices) {
        b[j] = j
    }
    ReverseArrayCopy(a,b)

    for(printArray in 0 until a.size) {
        print("a[$printArray] = ${a[printArray]} ")
    }
}
fun ReverseArrayCopy(a: IntArray, b: IntArray) {
    val ArrayNum = if(a.size <= b.size) a.size else b.size
    for(i in 0 until ArrayNum) {
        a[i] = b[(b.size - 1 - i)]
    }
}

// 배열 b의 요소를 배열 a에 역순으로 Copys