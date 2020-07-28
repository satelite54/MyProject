package com.project.kotlinstudy

fun main() {
    b(::a)

    val c: (String)-> Unit = {str -> println("$str 람다함수")}
//    val c = {str: String -> println("$str 람다함수")} // 이렇게도 사용가능 타입추론이 되어서
    b(c)
}

// 고차함수란 함수를 마치 클래스에서 만들어낸 인스턴스 취급하는 방법
// 함수를 파라미터로 넘길 수 있고 '결과값으로 반환'받을 수도 있는 방법

fun a (str: String) {
    println("$str 함수 a")
}

fun b(function: (String)-> Unit) {
    function("b가 호출한")
}