package com.project.kotlinstudy

fun main() {
    var a: Any = 1
    doWhen(a)
    a = "문자 넣을께.."
    doWhen(a)
    a = 9213213213211231332f
    doWhen(a)
}

fun doWhen (a: Any) {
    var doWhenValue = when(a) {
        is Int -> println("정수입니다.")
        is Long -> println("Long 타입입니다.")
        is String -> println("String 타입입니다.")
//        !is String -> println("String 타입이 아닙니다.")
        else -> "어떤 조건도 만족하지 않습니다."
    }

    println(doWhenValue)
}