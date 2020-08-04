package com.example.algorithm

fun main() {
    var a = 6
    spira(a)
}

fun spira(n : Int) {
    for(i in 1..n) {
        for(j in 1..n - i) { // " "을 출력하는 부분
            print(" ")
        }
        for(k in 1 until i * 2) { // "*"을 출력하는 부분
            if(k == i * 2 - 1) {
                println("*")
                break
            }
            print("*")
        }
    }
}