package chap06.section3

class KCoustomer {
    companion object {
        const val strLEVEL = "INTERMEDIATE"
        @JvmStatic fun login() = println("Login...") // 애노테이션 표기 사용
        @JvmField val JOB = KJob() // 특정 자료형을 사용하기 위한 애노테이션
    }
}

class KJob {
    var strtitle: String = "Programmer"
}