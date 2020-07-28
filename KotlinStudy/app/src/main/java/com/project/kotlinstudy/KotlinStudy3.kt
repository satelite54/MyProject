package com.project.kotlinstudy

fun main() {

    var nA = 1
    var nB = 2
    var nC = 3

    println(add(nA, nB, nC))
}

//fun add (a: Int, b: Int, c: Int): Int {
//    return a + b + c
//}

fun add (a: Int, b: Int, c: Int): Int = a + b + c // 단일 표현식 함수 // 반환형의 타입 추론 가능