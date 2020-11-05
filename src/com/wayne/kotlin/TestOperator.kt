package com.wayne.kotlin

/**
 * 操作符、运算符重载
 */
fun main() {
    val s = "HelloWorld"
    println(s.minus("World"))
    println(s minus "World")
    println(s - "World")
}

/**
 * 字符串移除
 */
infix operator fun String.minus(other: String): String {
    return this.replace(other, "")
}

/**
 * 字符串连加
 */
infix operator fun String.times(count: Int): String {
    return (1..count).joinToString("") { this }
}

/**
 * 强转的扩展方法
 */
fun <T> Any.safeAs(): T? {
    return this as? T
}
