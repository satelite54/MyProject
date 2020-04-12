package chap07.section01

interface Bird {
    val wings: Int
    fun fly()
    fun jump() {
        println("bird jump!") // 새는 점프라는 메서드가 구현되어있다.
    }
}

interface Horse {
    val maxSpeed: Int
    fun run()
    fun jump () {
        println("jump!, max speed: $maxSpeed") // 말도 점프라는 메서드가 구현되어있다.
    }
}

class Pegasus: Bird, Horse { // 페가수스는 새와 말의 인터페이스 두가지를 가진다.
    override val wings: Int = 2 // 구현이 안되어 있는 인터페이스는 모두 구현해 주어야함
    override val maxSpeed: Int = 100
    override fun fly() { // 페가수스에서 Bird 인터페이스에 fly라는 메서드를 정의 한다.
        println("Fly!")
    }

    override fun run() { // 페가수스에서 Horse 인터페이스에 run이라는 메서드를 정의 한다.
        println("Run!")
    }

    override fun jump() { // 페가수스는 말이라는 인터페이스 안에서 jump 라는 메서드를 쓰고 그냥 일반적인 말이 아닌 말의 특성을 가진 페가수스가 점프했다는 것을 메서드에 쓴다.
        super<Horse>.jump()
        println("Pegasus jump!")
    }
}

fun main() {
    val pegasus = Pegasus() // Bird 인터페이스와 Horse 인터페이스를 가진 페가수스 객체를 생성한다.
    pegasus.fly() // pegasus 객체에서 Bird 인터페이스의 fly 메서드를 쓴다.
    pegasus.run() // run도 같은 맥락으로 Horse 인터페이스의 run 매서드를 쓴다.
   pegasus.jump() // 페가수스는 새의 점프가 아닌 말의 점프를 이용하고 말이아닌 페가수스가 점프했다는 걸 알려준다.
}
// 헷갈린다..
// 추상 클래스와 인터페이스는 상속 받는 클래스 혹은 구현하는 인터페이스 안에 있는 추상 메소드를 구현하도록 강제한다.

// 상속은 슈퍼클래스의 기능을 이용하거나 확장하기 위해서 사용하고, 다중상속의 모호성을 방지하지 위해 하나만 상속가능함..
//  만약 부모클래스가 같은 이름을 가진 함수가 2개 일경우 어떤 것을 실행할지 모르기 때문에.. 헷갈린다 ㅠㅠ
// 상속에서는 구현을 강제하지 않음 그래서 부모클래스에 따라 자식 클래스들이 변경이 될 때가 있음..

// 반면 인터페이스는 해당 인터페이스를 구현한 객체들에 대해서 구현에 따른 동일한 동작을 보장 받기 위해 쓴다..  (아직도 헷갈리네..)

// 상속
// 만약에 비행기라는 클래스와 차라는 클래스를 상속 받는 탈것이라는 클래스가 있다고 치자.. 두 부모 클래스 안에는 drive라는 함수가 있다. 이 클래스에서
// 두 클래스를 상속받으려 할 경우 다중 상속의 모호성 때문에 java에서는 하나만 상속 할 수 있게 막아버림.. 

// 인터페이스
// 만약에 차라는 클래스가 있고 탈것이라는 인터페이스와 엔진이라는 인터페이스를 포함해 다중상속과 같은? 느낌을 줄 수 있다.
// 탈것에서의 drive 함수와 엔진에서의 drive 함수가 다른 구현부를 가져야만 된다. 개념이 다르기 때문
// 인터페이스에서는 이러한 각각의 drive 함수에 대한 구현을 강제함.

// 하.. 일단 더 공부하자
// [KTH 2020-04-12 오후 6:02]