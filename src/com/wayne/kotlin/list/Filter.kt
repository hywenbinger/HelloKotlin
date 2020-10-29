package com.wayne.kotlin.list

/**
 * 几个集合中常用的高阶函数
 *      1.过滤filter
 *      2.映射map
 *      3.聚合reduce
 */
fun main() {

    val list = listOf("aa", "ab", "ba", "bb")
    list.filter {
        // filter函数中的 Lambda 表达式返回布尔值
        // 返回true的元素进入下一个函数，false的元素被过滤掉
        it.startsWith("a") && it.endsWith("b")
    }.forEach {
        println("过滤后循环遍历list：element=$it")
    }

    println("-----------------------------------")

    val map = mapOf(1 to "11", 2 to "22")
    map.filter {
        it.value.startsWith("2")
    }.forEach {
        println("过滤后循环遍历map,：key=${it.key}, element =${it.value}")
    }
    map.map {
        it.key
    }.forEach {
        println(it)
    } 
    val sum = map.map {
        it.key
    }.reduce { total, score ->
        total + score
    }
    println(sum)


}