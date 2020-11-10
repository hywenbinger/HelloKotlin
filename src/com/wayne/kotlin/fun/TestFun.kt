package com.wayne.kotlin.`fun`

/**
 * 函数 VS 方法
 *      1.【方法】可以认为是函数的一种特殊类型
 *      2.从形式上，有receiver的【函数】即为【方法】
 *
 * 函数类型
 *
 * 函数引用
 *      1.类似C语言中的函数指针，用于函数传递
 *      2.使用【::函数名】获取函数引用
 */
fun main(args: Array<String>) {

    /**
     * 由于foo函数有重载，所以必须显示声明函数引用的类型
     */
    val f1: () -> Unit = ::foo
    val f2: (Int) -> String = ::foo
    println(f2.invoke(100)) // f2.invoke(100) = f2(100)

    /**
     * 注意下面两种写法(绑定和未绑定receiver)的函数类型是不一样的
     */
    val foo = Foo()
    val b1 = Foo::bar
    println(b1(foo, 10))
    val b2 = foo::bar
    println(b2(10))

    /**
     * 下面两种写法是等价的
     */
    "test".let(::println)
    println("test")
}

/**
 * 函数类型：()->Unit = Function0<Unit>
 * 函数引用：::foo
 */
fun foo() {

}

/**
 * 函数类型：(Int)->String = Function1<Int, String>
 * 函数引用：::foo
 */
fun foo(p: Int): String {
    return p.toString()
}

class Foo {
    /**
     * 函数类型：Foo.(Int) -> String = (Foo, Int) -> String = Function2(Foo, Int, String)
     * 函数引用：Foo::bar
     */
    fun bar(p: Int): String {
        return p.toString()
    }
}

//-------------------------------------------------------------------------------------------------

/**
 * 数组参数
 */
fun arrayFun(args: IntArray) {
    println(args.contentToString())
}

fun testArrayFun() {
    val intArray = intArrayOf(1, 2)
    ::arrayFun.invoke(intArray)
}

/**
 * 可变参数
 */
fun varargFun(vararg args: Int) {
    println(args.contentToString())
}

fun testVarargFun() {
    val intArray = intArrayOf(1, 2)
    varargFun(1, 2)
    varargFun(*intArray) //【数组类型数据】传入【可变类型参数】，需要加【*】
}

/**
 * 多返回值
 */
fun multiReturnFun(): Triple<Int, Int, String> {
    return Triple(1, 2, "hello")
}

fun testMultiReturnFun() {
    val (a, b, c) = multiReturnFun()
    println("$a, $b, $c")
}

