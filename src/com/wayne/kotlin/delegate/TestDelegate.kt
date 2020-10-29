package com.wayne.kotlin.delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Kotlin委托的用法
 *      https://juejin.im/post/6874478352095412231
 */
fun main() {
    val person = Person()
    person.name = "  wayne   "
    println(person.name)
}

class Person {
    var name by TrimDelegate()
}

/**
 * 委托类
 *
 * 去掉String首尾的空格
 */
class TrimDelegate : ReadWriteProperty<Any?, String> {
    private var trimmedValue: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return trimmedValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        trimmedValue = value.trim()
    }

}