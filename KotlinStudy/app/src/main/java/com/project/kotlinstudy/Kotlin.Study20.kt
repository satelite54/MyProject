package com.project.kotlinstudy

//옵저버 패턴
fun main() {
    EventPrinter().start()
}

interface  EventListener {
    fun onEvent(count: Int)
}

class Countertwo(var listener: EventListener) {

    fun count() {
        for(i in 1..100) {
            if(i % 5 == 0) listener.onEvent(i)
        }
    }
}

class EventPrinter: EventListener {
    override  fun onEvent(count: Int) {
        print("${count}-")
    }

    fun start() {
        val counter = Countertwo(this)
        counter.count()
    }
}