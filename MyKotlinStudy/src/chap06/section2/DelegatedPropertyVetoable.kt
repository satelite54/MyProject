package chap06.section2

import kotlin.properties.Delegates

 fun main() {
     var max: Int by Delegates.vetoable(0) {// 초깃 값은 0
             prop, old, new -> // 람다식 매개변수로 프로퍼티, 기존 값, 새로운 값 지정
         new > old // 조건에 맞지 않으면 거부
     }

     println(max) //0
     max = 10
     println(max)

     max = 5 // 여기서는 기존값이 새 값보다 크므로 false. 따라서 5를 재할당하지 않음
     println(max) //10
 }

// vetoable 함수는 컬렉션과 같이 큰테이터를 다룰 때 유용
// [KTH 2020-04-11 오후 8:19]
