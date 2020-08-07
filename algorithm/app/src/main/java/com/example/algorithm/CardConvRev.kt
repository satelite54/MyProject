package com.example.algorithm

fun main() {
    var x = 127
    var y = 16
    var CArray = CharArray(6)
    CardConvRev().CardConvRev(x, y, CArray)
    for(i in 0 until CArray.size) {
        println(CArray[i])
    }
}

class CardConvRev {
    fun CardConvRev(x: Int, y: Int, d: CharArray) {
        var dchar = "0123456789ABCDEFGHIJKLMNOPQRSTUVWSYZ"
        var dlight : Int = 0
        var tempX = x
        for(i in 0 until d.size) {
            d[dlight++] = dchar[x % y]
            tempX /= y
            if(tempX == 0)
                break
        }
    }
}