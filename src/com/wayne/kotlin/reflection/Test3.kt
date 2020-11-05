package com.wayne.kotlin.reflection

import com.wayne.kotlin.times
import java.lang.IllegalArgumentException
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

data class UserDao(val age: Int, val name: String)

/**
 * Model映射
 */
fun main() {

    test()

    println("-" * 38)

    UserDao::class.memberProperties.map {
        println(it)
    }

}

fun test() {
    val mapUser = mapOf("age" to 18, "name" to "wayne")
    println(mapUser)

    /**
     * mapAs()的代码分解
     */
    val kFunction = UserDao::class.primaryConstructor!! // 获取主构造函数
    val parameters = kFunction.parameters // 获取函数的参数列表
    val list = parameters.map {
        it to (mapUser[it.name] ?: if (it.type.isMarkedNullable) null
        else throw IllegalArgumentException("${it.name} is required but missing."))
        // 参数类型标注不可空，但是数据为空，就抛出异常
    }
    val map = list.toMap()
    val userDao = map.let(kFunction::callBy) // 等价于 val userDao = kFunction.callBy(map)
    println(userDao)
}

inline fun <reified To : Any> Map<String, Any?>.mapAs(): To {
    return To::class.primaryConstructor!!.let {
        it.parameters.map { parameters ->
            parameters to (this[parameters.name] ?: if (parameters.type.isMarkedNullable) null
            else throw IllegalArgumentException("${parameters.name} is required but missing."))
        }.toMap().let(it::callBy)
    }
}

inline fun <reified From : Any, reified To : Any> From.mapAs(): To {
    return From::class.memberProperties.map {
        it.name to it.get(this)
    }.toMap().mapAs()
}