package com.wayne.kotlin.lambda


/**
 * 懒加载
 */
fun main() {
    val num by lazy { getNum() }

    println(num)
}

fun getNum(): Int {
    println("get num")
    return 100
}
