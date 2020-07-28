package com.project.kotlinstudy

fun main() {
    var a = 7

    if(a > 10) { // 비교연산자 < <= >= > == 할당연산자와 혼동 방지
        println("a는 10보다 크다")
    }
    else {
        println("a는 10보다 작다")
    }

    a !is Int
}