package com.wayne.kotlin.`object`

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 代理
 */
fun main() {
    val user = User()
    user.name = "hello"
    println(user.name)

    user.age = 20
    user.age = 30
}

class User {
    var name: String by MyDelegate()

    var age:Int by Delegates.observable(18){
        property: KProperty<*>, oldValue: Int, newValue: Int ->  println("$property : $oldValue -> $newValue")
    }
}

/**
 * 属性name的委托对象
 */
class MyDelegate {

    //operator所修饰的函数是运算符重载函数

    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("MyDelegate setValue $property")
        return property.name
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("MyDelegate setValue $value")
    }
}