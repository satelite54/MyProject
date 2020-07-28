package com.project.kotlinstudy

//fun main() {
//    //조건형 반복문
//
//    var a = 0
//
//    while(a < 5) {
//        println("전 ${a++}") // a++ 후위연산자 // ++a 전위연산자
//        println("후 ${++a}")
//    }
//}

fun main() {
//    for(i in 0..9 step 3) {
//        print(i)
//    }

    for(i in 9 downTo 0 step 3)
        print(i)
}