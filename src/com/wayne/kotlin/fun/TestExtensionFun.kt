package com.wayne.kotlin.`fun`

fun main() {

    val mode = ExtensionTest("wayne")
    mode.print("My name is")

    val msg = mode go "My name is"
    println(msg)
}

class ExtensionTest(var name: String)

/**
 * 扩展函数
 *      如果扩展函数和成员函数相同，那么调用时优先执行成员函数
 */
fun ExtensionTest.print(msg: String) = println("$msg $name")

/**
 * 中缀函数
 *      中缀函数只能有一个参数
 *      中缀函数不能是顶层函数，只能是成员函数或扩展函数
 *      不加【infix】关键字，就是一个普通的扩展函数
 */
infix fun ExtensionTest.go(msg: String): String = "$msg $name"
