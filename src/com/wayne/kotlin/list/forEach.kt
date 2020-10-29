package com.wayne.kotlin.list

/**
 * 遍历集合的高阶函数
 *      1.forEach
 *      2.forEachIndexed
 */
fun main() {

    val array = arrayOf("aa", "ab", "ac")
    println("遍历array")
    val forEach = array.forEach {
        println("element=$it")
    }
    println(forEach)

    val forEachIndexed = array.forEachIndexed { index, item ->
        println("{index=$index, element=$item}")
    }
    println(forEachIndexed)

    println("-----------------------------------")

    val map = array.map {
        println(it)
    }
    println(map)

    println("-----------------------------------")

    val list = listOf(1, 1, 2, 2)
    println("遍历list")
    list.forEach {
        println("element=$it")
    }
    list.forEachIndexed { index, item ->
        println("{index=$index, element=$item}")
    }
    println("-----------------------------------")

    val set = setOf(1, 2)
    println("遍历set")
    set.forEach {
        println("element=$it")
    }
    set.forEachIndexed { index, item ->
        println("{index=$index, element=$item}")
    }
    println("-----------------------------------")

    val mapTest = mapOf(1 to "11", 2 to "22")
    println("遍历map")
    mapTest.forEach { (key, value) ->
        println("key=$key, value=$value")
    }
    mapTest.forEach {
        println("key=${it.key}, value=${it.value}")
    }
    println("-----------------------------------")
}