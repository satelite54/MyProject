package com.project.kotlinstudy

fun main() {
    var nA: Int = 54321

    var LB: Long = nA.toLong() // 명시적 형변환

    println(LB)

    // 배열

    var nArr = arrayOf(1, 2, 3, 4, 5) // 처음 선언했을 때의 전체 크기를 변경 할 수 없지만 빠른 실행 속도
    var nullArr = arrayOfNulls<Int>(5)

    nArr[0] = 8

    for(element in nArr)
        println("$element")
}