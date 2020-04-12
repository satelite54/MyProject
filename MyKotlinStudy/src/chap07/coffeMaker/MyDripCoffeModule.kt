package chap07.coffeMaker

class MyDripCoffeModule : CoffeModule {
    companion object {
        val electricHeater: ElectricHeater by lazy { // lazy를 이용한 지연 초기화
            ElectricHeater()
        }
    }

    private val _thermosiphon : Thermsiphon by lazy { // 임시적인 private 프로퍼티
        Thermsiphon(electricHeater)
    }

    override fun getThermosiphon(): Thermsiphon = _thermosiphon
}