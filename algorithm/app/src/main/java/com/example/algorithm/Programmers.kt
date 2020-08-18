package com.example.algorithm

fun main() {
    var answer = arrayListOf<Int>(5, 9, 7, 10)
    var divisor = 5
    var dsadsa = IntArray(1)
    answer.toIntArray()
    var result = Programmers().solution(answer, divisor)

    for(i in result) {
        println(result[i])
    }
}

class Programmers {
    fun solution(arr: arrayListOf, divisor: Int): IntArray {
        var answer = intArrayOf()

        var Flag = false

        for(i in 0 until arr.size) {
            if(0 == (arr[i] % divisor)) {
                answer.set(arr[i])
                Flag = true
            }
        }
        if(Flag == false)
            answer.plus(-1)
        answer.sort()
        return answer
    }
}