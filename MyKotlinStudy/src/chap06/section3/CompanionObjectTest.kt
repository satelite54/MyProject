package chap06.section3

class Person {
    var nId: Int = 0
    var strName: String = "Youngdeok"
    companion object {
        var strLanguage: String = "Korean"
        fun work() {
            println("working..")
        }
    }
}

fun main() {
    println(Person.strLanguage) // 인스턴스 생성하지 않고 기본값 사용
    Person.strLanguage = "English" // 기본값 변경 가능
    println(Person.strLanguage) // 변경된 내용 출력
    Person.work() // 매서드 실행
    // println(Person.strName) // strName은 컴페니언 객체가 아니여서 오류가 발생함
}

// Person 클래스의 strLanguage객체는 생성 없이도 접근할 수 있게 되었음. 컴페니언 객체는 실제 객체의 싱글톤으로 정의됨.
// 싱글톤(Singleton) : 전역 변수를 사용하지 않고 객체를 하나만 생성하도록 하며, 생성된 객체를 어디에서든지 함조할 수 있도록 하는 디자인 패턴의 하나
// 객체가 서로 동일한 정보를 가질 때 하나의 메모리만 유지해 자원의 낭비를 줄일 수 있기 때문!
// 디자인 패턴 : 소프트웨어 설계에서 공통적인 문제에 대한 표준적인 패턴을 만들어 적용할 수 있게 한 기법. 패턴의 종류는 생성과 주조 행위로 나뉨. 싱글톤은 생성 패턴 중 하나.
// [KTH 2020-04-11 오후 8:37]