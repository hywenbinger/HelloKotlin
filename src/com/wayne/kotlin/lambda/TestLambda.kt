package com.wayne.kotlin.lambda

/**
 * 函数式编程
 *
 * 函数与其他数据类型一样
 * Kotlin中每一个函数都有一个类型，称为【函数类型】，(参数列表中的参数类型 -> 返回类型)
 * 函数类型是一种数据类型，它与 Int、Boolean等数据类型在使用场景上没有区别
 *
 * 函数引用：引用一个已经定义好的、有名字的函数，使用 [::函数名]
 *      为什么用::引用呢？因为只有加了 [::] 函数才能变成对象
 *
 * Lambda 表达式是其实就是一种匿名函数
 * Lambda 的返回值不是用 return 来返回，而是直接取最后一行代码的值
 * 注意：Lambda 的返回值别写 return，如果你写了，它会把这个作为它外层的函数的返回值来直接结束外层函数。
 *       当然如果你就是想这么做那没问题啊，但如果你是只是想返回 Lambda，这么写就出错了。
 *
 * 注意：匿名函数其实不是函数，而是对象(和 [::函数名] 是一个东西)，是函数类型的对象
 *
 * 创建一个【函数类型的对象】有三种方式：
 *      1.双冒号加函数名
 *      2.匿名函数
 *      3.Lambda
 *
 * 一定要记住：【双冒号加函数名、匿名函数和 Lambda 本质上都是函数类型的对象】
 *             在 Kotlin 里，匿名函数不是函数，Lambda 也不是什么玄学的所谓「它只是个代码块，没法归类」，
 *             Kotlin 的 Lambda 可以归类，它属于函数类型的对象。
 */
fun main() {

    val a = fun(i: Int): String { return "匿名函数a-->$i" }
    println(a) // (kotlin.Int) -> kotlin.String
    println(a(1000)) // 匿名函数:1000

    val b = { println("这是无参的lambda") }
    println(b) // () -> kotlin.Unit
    b()

    val c: (Boolean) -> String = { bb: Boolean -> "匿名函数c-->$bb" }
    println(c) // (kotlin.Boolean) -> kotlin.String
    println(c(true))

    val d: (Int) -> Boolean = { it == 100 }
    println(d) // (kotlin.Int) -> kotlin.Boolean
    println(d(100))

    val e = { i: Int -> i == 100 }
    println(e) // (kotlin.Int) -> kotlin.Boolean
    println(e(1000))

    println("直接调用函数")
    val m = operationAdd(2, 2)
    println("使用函数类型对象调用")
    val n = (::operationAdd)(2, 2) // 实际上会调用 (::operationAdd).invoke(1)
    // 直接调用函数没毛病，为什么使用函数类型对象也能调用？
    // 对象是不能加个括号来调用的，对吧？但是函数类型对象可以，为什么？
    // 因为这其实是个假的调用，它是 Kotlin 的语法糖，
    // 实际上你对一个函数类型的对象加括号、加参数，它真正调用的是这个对象的 invoke() 函数

    println("这是有参的lambda")
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    println("1 + 1 = ${sum(1, 1)}")
    //省略了返回类型，推断出的返回值类型不为Unit时，它的返回值即为->符号后面代码的最后一个表达式的类型
    val result1 = { x: Int, y: Int -> x - y }
    println("1 - 1 = ${result1(1, 1)}")
    val result2 = ::operationSub
    println("1 - 1 = ${result2(1, 1)}")


    println("lambda作为函数参数")
    val add = operation(10, 10) { x, y -> x + y }
    println("10 + 10 = $add")
    val sub = operation(10, 10) { x, y -> x - y }
    println("10 - 10 = $sub")

    val value1 = operation1(1, 1, ::operationAdd)
    println("1 + 1 = $value1")
    val value2 = operation1(1, 1) { x, y -> x - y }
    println("1 - 1 = $value2")

    println("lambda作为函数返回值")
    var ope = getOperation(Operation.SUB)
    println("100 - 100 = ${ope(100, 100)}")
    ope = getOperation(Operation.ADD)
    println("100 + 100 = ${ope(100, 100)}")
}

private fun operation(a: Int, b: Int, compute: (Int, Int) -> Int): Int {
    return compute(a, b)
}

private fun operation1(a: Int, b: Int, compute: (x: Int, y: Int) -> Int): Int {
    return compute(a, b)
}

private fun getOperation(type: Operation): (Int, Int) -> Int {
    return when (type) {
        Operation.ADD -> ::operationAdd
        Operation.SUB -> ::operationSub
    }
}

private fun operationAdd(x: Int, y: Int) = x + y

private fun operationSub(x: Int, y: Int) = x - y

enum class Operation {
    ADD,
    SUB
}