package com.wayne.kotlin.exception

import java.lang.Exception

/**
 * java中的异常包含【检查时异常】、【运行时异常】
 *
 * kotlin中的异常都是【运行时异常】
 */
fun main() {

//    try {
//        val a = 1 / 0
//    } catch (t: Throwable) {
//        println("message is ${t.message}")
//    }

    val result = try {
        10 / 0
    } catch (e: Exception) {
        -1
    } finally {
        println("finally......一般在这里释放资源")
        // 在 Kotlin 中使用 Java7 之后提供的自动资源管理技术
        // 所有可以自动管理的资源需要实现 Java 中的AutoCloseable接口，使用 use 函数管理资源
    }
    println("try-catch表达式:$result")

}