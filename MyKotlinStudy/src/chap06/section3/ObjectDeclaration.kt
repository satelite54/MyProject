package chap06.section3

object Ocustomer { // object 키워드를 사용한 방식 // companion 키워드와 동일하게 단일 인스턴스를 생성해서 처리 싱글톤
    var name = "Kildong"
    fun greetiong() = println("Hellow World!")
    val HOBBY = Hobby("Basketball")
    // 최초 접근에서 실행
    init {
        println("Init!")
    }
}

class CCustomer {
    companion object {
        const val HELLO = "hello" // 상수 표현
        var name = "Joosol"
        @JvmField val HOBBY = Hobby("Football")
        @JvmStatic fun greeting() = println("Hellow World!")
    }
}

class  Hobby(val name: String)

fun main() {
    Ocustomer.greetiong() // 객체의 접근시점
    Ocustomer.name = "Dooly"
    println("name = ${Ocustomer.name}")
    println(Ocustomer.HOBBY.name)

    CCustomer.greeting()
    println("name = ${CCustomer.name},  HELLO = ${CCustomer.HELLO}")
    println(CCustomer.HOBBY.name)
}