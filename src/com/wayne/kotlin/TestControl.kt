package com.wayne.kotlin

/**
 * 控制语句
 */
fun main() {

}

/**
 * Kotlin中无三元运算符
 */
fun testIf() {
    val numA = 1
    // 在Java中可以这么写，但是Kotlin中直接会报错
    // var numB: Int = (numA > 2) ? 3 : 5

    // kotlin中直接用if..else替代
    val numB: Int = if (numA > 2) 100 else -100
    println("numB = > $numB")
}

/**
 * Kotlin废除了Java中的for(初始值;条件;增减步长)
 */
fun testFor() {
    /**
     * until[n,m)
     */
    for (i in 0 until 2) {
        println(i) // 0 1
    }

    /**
     * ..[n,m]
     */
    for (i in 0..2) {
        println(i) // 0 1 2
    }

    /**
     * rangeTo[n,m]
     */
    for (i in 0.rangeTo(2)) {
        println(i) // 0 1 2
    }

    /**
     * downTo[n,m]
     */
    for (i in 2 downTo 0) {
        println(i) // 2 1 0
    }

    /**
     * step设置步长
     */
    for (i in 0..6 step 2) {
        println(i) // 0 2 4 6
    }

    /**
     * 迭代：遍历字符串
     */
    for (s in "hello") {
        println(s) // h e l l o
    }

    /**
     * 迭代：遍历数组
     */
    val array = arrayOf("a", "b")
    for (value in array) {
        println(value) // a b
    }
    for (index in array.indices) {
        println(index) // 0 1
    }
    for ((index, value) in array.withIndex()) {
        println("$index=$value") // 0=a 1=b
    }
    val iterator = array.iterator()
    while (iterator.hasNext()) {
        println(iterator.next()) // a b
    }
}

/**
 * Kotlin废除掉了Java中的switch语句
 */
fun testWhen() {
    val x = 2
    when (x) {
        1 -> println(1)
        2 -> println(2)
        else -> println("unknown number")
    }
    // 输出结果：2

    when (x) {
        1, 2 -> println("<=2") // x=1或x=2，都执行这个分支
        3, 4 -> println(">2")
        else -> println("unknown number")
    }
    // 输出结果：<=2

    when (x > 2) {
        true -> println(">2")
        false -> println("<=2")
    }
    // 输出结果：<=2

    when (x) {
        in 0..2 -> println("<=2")
        else -> println(">2")
    }
    // 输出结果：<=2
}