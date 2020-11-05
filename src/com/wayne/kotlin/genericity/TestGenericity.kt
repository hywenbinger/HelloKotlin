package com.wayne.kotlin.genericity

/**
 * 认识泛型
 *      1.泛型是一种类型层面的抽象
 * 泛型的声明
 *      1.函数声明泛型：fun <T> maxOf(a: T, b: T): T
 *      2.类声明泛型：sealed class List<T>
 */
fun main() {

}

fun <T> test(a: T) {

}

/**
 * 问题：a和b都是泛型，那么怎么比较大小呢？
 *
 * 答案：可通过【泛型约束】解决，那么传过来的T，必须实现Comparable接口
 */
fun <T : Comparable<T>> maxOf(a: T, b: T): T {
    return if (a > b) a else b
}

/**
 * 【泛型的多约束】，使用【where】关键字
 */
fun <T> callMax(a: T, b: T) where T : Comparable<T>, T : () -> Unit {
    return if (a > b) a() else b()
}

/**
 * 【多个泛型参数的多约束】
 */
fun <T, R> callMax(a: T, b: T): R where T : Comparable<T>, T : () -> R, R : Number {
    return if (a > b) a() else b()
}


