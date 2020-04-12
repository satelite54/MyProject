package chap07.coffeMaker

interface Heater {
    fun on()
    fun off()
    fun isHot() : Boolean
}

class ElectricHeater (var heating: Boolean = false) : Heater {
    override fun on() {
        println("ElectricHeater On")
        heating = true
    }

    override fun off() {
        println("ElectricHeater Off")
    }

    override fun isHot(): Boolean {
        return  heating
    }
}

