package chap06.section3

import java.lang.reflect.Field

//Q1

//class User(_name:String, _age: Int) {
//    var name: String = _name
//    set(value) {
//        println("The name was changed")
//        field = value.toUpperCase()
//    }
//
//    var age: Int = _age
//}

//Q2

class PersonName {
    lateinit var name:String
}

fun main () {
    //Q1s

    val PersonName: PersonName
    
//    var name = "hongKildong"
//    var age = 14
//    var USER = User(name, age)
//    println("${USER.name}")
//
//    USER.name = "dwdwdwdw"
//    println("${USER.name}")
}