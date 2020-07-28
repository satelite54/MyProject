package com.project.kotlinstudy
// 클래스는 값과 그 값을 사용하는 기능들을 묶어 놓은 것

// 고유의 특징 값 속성
// 기능을 구현한 함수로 이루어짐
fun main() {
    var a = Person("박보영", 1990)
    var b = Person("전정국", 1997)
    var c = Person("김태헌", 1994)

    a.introduce()
    b.introduce()
    c.introduce()
}
// 클래스는 인스턴스를 만드는 틀
// 인스턴스란 클래스를 이용해서 만들어내는 서로 다른 속성의 객체를 지칭하는 용어
class Person (var name: String, var birthYear: Int) {
    fun introduce () {
        println("안녕하세요. 제 이름은 $name 이고 생일은 $birthYear 입니다.")
    }
}