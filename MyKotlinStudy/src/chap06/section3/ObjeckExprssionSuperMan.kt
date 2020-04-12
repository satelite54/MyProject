package chap06.section3

open class Superman() {
    fun work() = println("Taking photos")
    fun talk() = println("Talking with people")
    open fun fly() = println("Flying in the air")
}

fun main() {
    val pretendedMan = object: Superman() { // object 표현식으로 fly 구현의 재정의
        override fun fly() = println("I'm not a real superman. I can't fly!")
    }

    pretendedMan.work()
    pretendedMan.talk()
    pretendedMan.fly()
}
// 익명 객체는 Superman 클래스를 상속해 fly 메서드를 오버라이딩
// [KTH 2020-04-12 오후 12:39]