package com.wayne.kotlin.reflection

import com.wayne.kotlin.times
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * 为数据类实现DeepCopy
 */
fun main() {
    val person = Person(100, Group(1))
    val personCopy = person.copy()

    println(person === personCopy) // false
    println(person.group === personCopy.group) // true
    // 数据类的copy其实是浅拷贝
    // 那么可能就有个问题：修改了person的group，personCopy的group也跟着变了，显然在某些使用场景下是不满足需求的
    // 如下代码所示：
    println("person group id = ${person.group.id}, personCopy group id = ${personCopy.group.id}") // 1, 1
    person.group.id = 2
    println("person group id = ${person.group.id}, personCopy group id = ${personCopy.group.id}") // 2, 2

    println("--" * 38)

    val personDeepCopy = person.deepCopy()
    println(person === personDeepCopy) // false
    println(person.group === personDeepCopy.group) // false
    println("person group id = ${person.group.id}, personDeepCopy group id = ${personDeepCopy.group.id}") // 2, 2
    person.group.id = 3
    println("person group id = ${person.group.id}, personDeepCopy group id = ${personDeepCopy.group.id}") // 3, 2
}

data class Person(var id: Int, var group: Group)
data class Group(var id: Int)

/**
 * 深度拷贝扩展方法
 */
fun <T : Any> T.deepCopy(): T {
    if (!this::class.isData) {
        return this // 如果不是数据类，直接返回
    }
    return this::class.primaryConstructor!!.let { primaryConstructor ->
        println(primaryConstructor)
        primaryConstructor.parameters.map { parameters ->
            val value = (this::class as KClass<T>).memberProperties.first {
                it.name == parameters.name
            }.get(this)
            if ((parameters.type.classifier as? KClass<*>)?.isData == true) {
                parameters to value?.deepCopy()
            } else {
                parameters to value
            }
        }.toMap().let(primaryConstructor::callBy)
    }
}