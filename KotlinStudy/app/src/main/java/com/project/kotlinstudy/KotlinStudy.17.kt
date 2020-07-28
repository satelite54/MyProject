package com.project.kotlinstudy

//스코프 함수
//apply 인스턴트 생성 전에  초기화할 때 사용
//처리가 끝나면 인스턴스를 반환
fun main() {
    var a = Book("태헌이의 코를린", 10000).apply {// apply는 인스턴스 자신을 다시 반환하므로
        name = "[초특가]" + name
        discount()
    }

    a.run {// With는  run과 동일한 기능을 가지지만 인스턴스를 참조연산자 대신 패러미터로 받는다는 차이점이 있다. // 처리가 끝나면 최종값을 반환
        println("상품명: $name, 가격: $price,원")
    }
}

class Book(var name: String, var price: Int) {
    fun discount() {
        price -= 2000
    }
}