package com.wayne.kotlin.reflection

class Model {

    var age: Int = 18
    var name: String = "wayne"
    fun myFun() {
        println("age is $age, name is $name")
    }

    companion object {
        fun myStatic() {
            println("static fun")
        }
    }

}