package chap07.coffeMaker

class CoffeeMaker(val coffeModule: CoffeModule) {
    fun brew() {
        val theSiphon: Thermsiphon = coffeModule.getThermosiphon()
        theSiphon.on()
        theSiphon.pump()
        println("Coffee, here! Enjoy~!")
        theSiphon.off()
    }
}

fun main() {
    val coffeeMaker = CoffeeMaker(MyDripCoffeModule())
    coffeeMaker.brew()
}