package chap07.coffeMaker

class Thermsiphon(heater: Heater) : Pump, Heater by heater { // 위임의 사용
    override fun pump() {
        if(isHot()) { // Heater by heater를 통해 isHot 그대로 사용
            println("[Thermosiphon] pumping...")
        }
    }
}