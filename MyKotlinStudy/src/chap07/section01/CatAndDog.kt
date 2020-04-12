package chap07.section01

open class Animal(val name: String)

class Dog(name: String, override var category: String) : Animal(name), Pet {
    override var species: String = "dog"
    override fun feeding() {
        println("Feed the dog a bone")
    }
}

class Master {
    fun playWithPet(pet: Pet) {
        println("Engjoy with my ${pet.species}")
    }
//    fun playWithPet(dog: Dog) {
//        println("Engjoy with my dog")
//    }
//    fun playWithPet(cat: Cat) {
//        println("Engjoy with my cat")
//    }
}

fun main() {
    val master= Master()
    val dog = Dog("Toto", "Small")
    val cat = Cat("Coco", "BigFat")
    master.playWithPet(dog)
    master.playWithPet(cat)
}