package com.project.kotlinstudy

//as는 변수를 호환되는 자료형으로 변환해주는 캐스팅 연산자
//is는 변수가 자료형에 호환되는지를 먼저 체크한 후 변환해준다.
fun main() {
    var a = Drink()
    a.drink()

    var b: Drink = Cola()
    b.drink()

    if(b is Cola) { // 조건문안에서만 잠시 다운 캐스팅
        b.washDishes()
    }
}

open class Drink {
    var name = "음료"

    open fun drink() {
        println("${name}를 마십니다.")
    }
}

class Cola: Drink() {
    var type = "콜라"

    override fun drink() {
        println("${name}중에 ${type}를 마십니다.")
    }

    fun washDishes() {
        println("${type}로 설거지를 합니다.")
    }
}