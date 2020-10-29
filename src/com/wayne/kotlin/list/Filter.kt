package com.wayne.kotlin.list

/**
 * 几个集合中常用的高阶函数
 *      1. filter：过滤
 *      2. map/flatMap：映射
 *      3. sum：所有元素求和
 *      4. reduce：元素依次按规则聚合，结果与元素类型一致
 *      5. fold：给定初始值，元素依次按规则聚合，结果与初始值类型一致
 *
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
    val newList = list.map {
        it + it
    }
    println(newList)
    val newNewList = list.flatMap {
        0 until 3
    }
    println(newNewList)

    println("-----------------------------------")

    val map = mapOf(1 to "11", 2 to "22", 3 to "33")
    map.filter {
        it.value.startsWith("2")
    }.forEach {
        println("过滤后循环遍历map：key=${it.key}, element =${it.value}")
    }
    val value = map.map {
        it.key
    }
    println(value)

    println("-----------------------------------")

    val sum = map.map {
        it.key
    }.sum()
    println(sum)

    val reduce = map.map {
        it.key
    }.reduce { acc, i ->
        acc + i
    }
    println(reduce)

    val fold = map.map {
        it.key
    }.fold("结果是：") { acc, i ->
        acc + i
    }
    println(fold)


}