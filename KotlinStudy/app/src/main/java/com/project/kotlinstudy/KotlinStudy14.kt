package com.project.kotlinstudy


//인터페이스 추상함수 , 일반함수 가질 수 있음 추상클래스는 생성자를 가질 수 있지만 인터페이스는 불가
// 구현 부가 있ㄲ는 함수는 Open 함수
// 구현 부가 없는 함수는 추상함수
// 한번에 인터페이스 여러개 상속가능
// 인터페이스는 서로 다른 기능을 여러개에 물려주어야할 때 유용
fun main() {
    var Animal = DogCat()
    Animal.run()
    Animal.eat()
}

interface Runner {
    fun run()
}

interface  Eater {
    fun eat() {
        println("음식을 먹습니다.")
    }
}

class DogCat : Runner, Eater {
    override fun run() {
        println("우다다다 뜁니다.")
    }
    override fun eat() {
    println("허겁지겁 먹습니다.")}
}