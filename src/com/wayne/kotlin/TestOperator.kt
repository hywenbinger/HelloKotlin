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

infix operator fun String.minus(other: String): String {
    return this.replace(other, "")
}
