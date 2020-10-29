package com.wayne.kotlin.`object`

/**
 * 数据类
 */
fun main() {
    val data1 = MyData(age = 20, name = "wayne")
    println(data1)

    val data2 = data1.copy(name = "luna", female = true)
    println(data2)
}

data class MyData(var age: Int = 18, var name: String = "unknown", var female: Boolean = false)