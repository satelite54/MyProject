package chap07.section01

abstract class Printer {
    abstract fun print() // 추상 메서드
}

val myPrinter = object: Printer() {
    override fun print() {
        println("출력합니다.")
    }
}

fun main() {
    myPrinter.print()
}