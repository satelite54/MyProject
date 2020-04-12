package chap07.section01

import javax.management.monitor.StringMonitor
import kotlin.math.max

// 추상 클래스, 주 생성자에는 비추상 프로퍼티 선언의 매개변수 3개가 있음
abstract class Vehicle(val name: String, val color: String, val weight: Double) {

    // 추상 프로퍼티(반드시 하위 클래스에서 재정의해 초기화해야함)

    abstract var maxSpeed: Double

    //일반 프로퍼티(초깃값인 상태를 저장할 수 있음)
    var year = "2018"

    abstract fun start()
    abstract fun stop()

    fun displaySpecs() {
        println("Name: ${name}, Color: $color, Weight: $weight, Year: $year, Max Speed: $maxSpeed")
    }
}

//Vehicle 클래스는 객체를 생성할 수 없고 탈것에 대한 기본 설계 역활을 함. 주생성자의 매개변수에 val이 사용되었으므로 프로퍼티가 됨,
//3개의 프로퍼티로 name, color weight는 abstract가 없는 일반 프로퍼티로 선언됨. year는 일반 프로퍼티로 초깃값으로 2018을 가짐
//adstract 키워드로 선언된 최대 속도를 나타내는 MaxSpeed 프로퍼티, 운행과 중단을 나타내는 start(), stop() 메서드는 특정구현내용이
//없는 추상 맴버이기 때문에 하위 클래스에서 재정의하거나 구현해야함. 반면에 year 프로퍼티나 displaySpecs() 메서드는 하위 클래스에서 재정의할
//필요가 없다.

// adstract 키워드는 자체가 상속과 오버라이등을 허용함

class  Car(name: String,
           color: String,
           weight: Double,
           override var maxSpeed: Double)
    : Vehicle(name, color, weight) {
    override fun start() {
        println("Car Started")
    }
    override fun stop() {
        println("Car Stopped")
    }
}

class  Motorcycle(name: String,
                  color: String,
                  weight: Double,
                  override var maxSpeed: Double): Vehicle (name, color, weight){
    override fun start() {
        println("BIke Started")
    }
    override fun stop() {
        println("Bike Stopped")
    }
}

fun main() {
    val car = Car("SuperMatiz", "yellow", 1110.0, 270.0)
    val motor = Motorcycle("DreamBike", "red", 173.0, 100.0)

    car.year = "2013"

    car.displaySpecs()
    car.start()
    motor.displaySpecs()
    motor.start()
}
// displaySpecs는 기존에 추상 클래스가 가지고 있던 일반 메서드, 각 객체의 start(), stop은 추상 메서드로부터 오버라이딩되어
// 하위 클래스에서 구현된 메서드