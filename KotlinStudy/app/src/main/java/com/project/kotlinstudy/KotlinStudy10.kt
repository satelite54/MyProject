package com.project.kotlinstudy

fun main() {
    var a = Personinit("박보영", 1990)
    var b = Personinit("전정국", 1997)
    var c = Personinit("김태헌", 1994)

    var d = Personinit("이루다")
    var e = Personinit("차은우")
}

class Personinit (var name: String, var birthYear: Int) {
    // 클래스를 만들 때 기본으로 선언 -> 기본 생성자
    // 필요에 따라 추가적으로 선언 -> 보조 생성자

    // 보조 생성자는 기본생성자와 다른 형태의 생성자를 제공
    // 인스턴스 생성시 편의를 제공하거나 추가적인 구문을 수행하는 기능을 제공

    init {
        println("${this.birthYear}년생 ${this.name}님이 생성되었습니다.")
    }

//   fun introduce () {
//        println("안녕하세요. 제 이름은 $name 이고 생일은 $birthYear 입니다.")
//    }

    constructor(name: String) : this(name, 1997) {
        println("보조 생성자가 사용되었습니다.")
    }
}