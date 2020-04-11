package chap06.section2

interface Car {
    fun go(): String
}

class  VanImpl(val  strpower: String) : Car {
    override fun go() = "은 짐을 적재하며 $strpower 을 가집니다."
}

class SportImpl(val strpower: String) : Car {
    override fun go() = "은 경주용에 사용되며 $strpower 을 가집니다."
}

class CarModel(val strmodel: String, impl: Car): Car by impl { // 프로퍼티 혹은 클래스 이름: 자료형 by 위임자
    fun carInfo() {
        println("$strmodel ${go()}") // 참조 없이 각 인터페이스 구현 클래스의 go()에 접근
    }
}

fun main() {
    val myDamas = CarModel("Damas 2010", VanImpl("100마력"))
    val my350z = CarModel("350Z 2008", SportImpl("350마력"))

    myDamas.carInfo() // carInfo의 다형성
    my350z.carInfo()
}

// 코틀린은 기본적으로 모든 클래스가 Final Class이다. 그래서 상속이나 직접 클래스의 기능 확장이 어려움.
// 표준 라이브러리의 무분별한 상속에 따른 복잡한 문제를 방지하기 위함임.
// 필요한 경우에만 위임을 통해 상속과 비슷하게 해당 클래스의 모든 기능을 사용하면서 동시에 기능을 추가 확장 구현할 수 있음.