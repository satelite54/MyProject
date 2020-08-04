package com.example.algorithm

import java.util.*

fun main() {
    var CScanner: Scanner = Scanner(System.`in`)
    println("키의 최대값을 구합니다.")
    print("사람의 수 : ")
    var num = CScanner.nextInt()

    var height = arrayOf(num)

    for(i in 0 until height.size) {
        println("height[" + i + "]:")
        height[i] = CScanner.nextInt()
    }

    println("최댓값은 " + MaxValueOfArray().maxof(height) + "입니다.")
}
class MaxValueOfArray{
    fun maxof(a: Array<Int>) : Int? {
        var max = a[0]
        for(i in 1..a.size) {
            if(a[i]!! > max!!)
                max = a[i]
        }
        return max
    }
}