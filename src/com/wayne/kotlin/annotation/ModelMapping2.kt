package com.wayne.kotlin.annotation

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor

/**
 *  加强版Model映射：通过策略类注解
 */
fun main() {
    val mapUser = mapOf("first_name" to "wayne", "last_name" to "han")
    println(mapUser)

    val userDao = mapUser.superMapAs<MyUserDao2>()
    println(userDao)
}


inline fun <reified To : Any> Map<String, Any?>.superMapAs(): To {
    val kFunction = To::class.primaryConstructor!! // 获取主构造函数
    val parameters = kFunction.parameters // 获取函数的参数列表
    val list = parameters.map {
        val parameterName = it.name!! // 参数名
        val value = this[parameterName]
        // 通过构造函数参数名未获取到数据，则从FieldName注解中读取参数名再获取数据
            ?: it.annotations.filterIsInstance<FieldName>().firstOrNull()?.name?.let(this::get)
            // 通过FieldName注解中读取参数名未获取数据，则通过MappingStrategy注解处理数据
            ?: To::class.findAnnotation<MappingStrategy>()?.klass?.objectInstance?.mapTo(parameterName)?.let(this::get)
            // 参数类型标注不可空，但是数据为空，就抛出异常
            ?: if (it.type.isMarkedNullable) null else throw IllegalArgumentException("$parameterName is required but missing.")
        it to value
    }
    val map = list.toMap()
    return kFunction.callBy(map)
}

@MappingStrategy(CamelToUnderScore::class)
data class MyUserDao2(
    val firstName: String,
    val lastName: String
)

@Target(AnnotationTarget.CLASS)
annotation class MappingStrategy(val klass: KClass<out NameStrategy>)

interface NameStrategy {
    fun mapTo(name: String): String
}

object UnderScoreToCamel : NameStrategy {
    // html_url -> htmlUrl
    override fun mapTo(name: String): String {
        return name.toCharArray().fold(StringBuilder()) { acc, c ->
            when (acc.lastOrNull()) {
                '_' -> acc[acc.lastIndex] = c.toUpperCase()
                else -> acc.append(c)
            }
            acc
        }.toString()
    }
}

object CamelToUnderScore : NameStrategy {
    // htmlUrl -> html_url
    override fun mapTo(name: String): String {
        return name.toCharArray().fold(StringBuilder()) { acc, c ->
            when {
                c.isUpperCase() -> acc.append('_').append(c.toLowerCase())
                else -> acc.append(c)
            }
            acc
        }.toString()
    }
}
