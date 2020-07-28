package com.project.kotlinstudy

// 상속

// 서브 클래스는 수퍼 클래스에 존재하는 속성과 '같은이름'의 속성을 가질 수 없음
// 서브 클래스가 생성 될 때는 반드시 수퍼 클래스의 생성자까지 호출해야함
fun main () {
    var a = Animal("별이", 5, "개")
    var b = Dog("별이", 5)

    a.introduce()
    b.introduce()
    b.bark()
}

open class Animal (var name: String, var age: Int, var type: String) {
    fun introduce() {
        println("저는 $type $name 이고, ${age}살 입니다.")
    }
}

class Dog (name: String, age: Int) : Animal (name, age, "개") {
    fun bark() {
        println("왈왈!")
    }
}