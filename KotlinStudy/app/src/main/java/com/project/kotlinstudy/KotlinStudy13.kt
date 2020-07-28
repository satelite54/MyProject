package com.project.kotlinstudy

//추상화는 선언부만 있고 기능이 구현된지 않은 추상함수
// 추상함수를 포함하는 추상 클래스
fun main() {
    var r = Rabbit()

    r.eat()
    r.sniff()
}

// 추상클래스는 미완성 클래스이기 때문에 단독으로 인스턴스 생성 불가
// 반드시 서브 클래스에서 상속으로 받아 서브 클래스에서 구현을 해 주어야 함
abstract class absAnimal {
    abstract fun eat()
    fun sniff() {
        println("킁킁")
    }
}

class Rabbit : absAnimal() {
    override fun eat() {
        println("당근을 먹습니다.")
    }
}
