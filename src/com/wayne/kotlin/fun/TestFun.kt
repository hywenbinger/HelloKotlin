package com.wayne.kotlin.`fun`

fun main(args: Array<String>) {

    val intArray = intArrayOf(1, 2)
    /**
     * 测试：数组参数
     */
    arrayFun(intArray)
    ::arrayFun.invoke(intArray)

    /**
     * 测试：可变参数
     */
    varargFun(1, 2)
    varargFun(*intArray) //【数组类型数据】传入【可变类型参数】，需要加【*】

    /**
     * 测试：多返回值
     */
    val (a, b, c) = multiReturnFun()
    println("$a, $b, $c")

}

/**
 * 数组参数
 */
fun arrayFun(args: IntArray) {
    println(args.contentToString())
}

/**
 * 可变参数
 */
fun varargFun(vararg args: Int) {
    println(args.contentToString())
}

/**
 * 多返回值
 */
fun multiReturnFun(): Triple<Int, Int, String> {
    return Triple(1, 2, "hello")
}