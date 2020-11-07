package com.wayne.kotlin.annotation

import java.lang.IllegalArgumentException
import kotlin.reflect.full.primaryConstructor

/**
 * 加强版Model映射：通过字段注解
 */
fun main() {
    val mapUser = mapOf("first_name" to "wayne", "last_name" to "han")
    println(mapUser)

    val userDao = mapUser.mapAs<MyUserDao1>()
    println(userDao)
}

inline fun <reified To : Any> Map<String, Any?>.mapAs(): To {
    val kFunction = To::class.primaryConstructor!! // 获取主构造函数
    val parameters = kFunction.parameters // 获取函数的参数列表
    val list = parameters.map {
        val value = this[it.name]
        // 通过构造函数参数名未获取到数据，则从FieldName注解中读取参数名再获取数据
            ?: it.annotations.filterIsInstance<FieldName>().firstOrNull()?.name?.let(this::get)
            // 参数类型标注不可空，但是数据为空，就抛出异常
            ?: if (it.type.isMarkedNullable) null else throw IllegalArgumentException("${it.name} is required but missing.")
        it to value
    }
    val map = list.toMap()
    return kFunction.callBy(map)
}

data class MyUserDao1(
    @FieldName("first_name")
    val firstName: String,
    @FieldName("last_name")
    val lastName: String
)

@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class FieldName(val name: String)