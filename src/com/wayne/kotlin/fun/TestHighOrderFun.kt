package com.wayne.kotlin.`fun`

import java.io.File

/**
 * 几个常用的高阶函数
 *      let
 *      apply
 *      run
 *      with
 *      also
 *      repeat
 *      takeIf
 *      takeUnless
 *      use
 */
fun main() {

}

/**
 * let
 *      1.it = receiver
 *      2.return block的返回值
 */
fun testLet() {
    val result = "testLetFun".let {
        println(it)
        println(it)
        100
    }
    println(result)

    //结果：
    //testLetFun
    //testLetFun
    //100
}

/**
 * apply
 *      1.block内可以调用receiver的任意函数
 *      2.return receiver
 */
fun testApply() {
    val list = mutableListOf(1)
    val value = list.apply {
        add(2)
        add(3)
        remove(3)
    }
    println(list)
    println(value)

    //结果：
    //[1, 2]
    //[1, 2]
}

/**
 * run
 *      1.block内可以调用receiver的任意函数
 *      2.return block的返回值
 */
fun testRun() {
    val list = mutableListOf(1)
    val value = list.run {
        add(2)
        add(3)
        remove(3)
        100
    }
    println(list)
    println(value)

    // 结果：
    // [1, 2]
    // 100
}

/**
 * with
 *      1.block内可以调用receiver的任意函数
 *      2.return block的返回值
 *      3.和 T.run{} 功能类似，不过它不是extension函数
 */
fun testWith() {
    val list = mutableListOf(1)
    val value = with(list){
        add(2)
        add(3)
        remove(3)
        100
    }
    println(list)
    println(value)

    //结果：
    //[1, 2]
    //100
}

/**
 * 另一个run
 *      1.执行block
 *      2.return block的返回值
 */
fun testOtherRun() {
    val value = run {
        val a = 100
        val b = 200
        a + b
    }
    println(value)

    // 结果：
    // 300
}

/**
 * also
 *      1.it = receiver
 *      2.return receiver
 */
fun testAlso() {
    val also = "test".also {
        it + "test"
    }
    println(also)

    //结果：
    //test
}

/**
 * repeat
 *      1.循环执行多次block
 *      2.it = 第几次循环
 */
fun testRepeat() {
    repeat(2) {
        println(it)
    }

    //结果：
    //0
    //1
}

/**
 * takeIf
 *      1.it = receiver
 *      2.满足block中条件，返回receiver，否则返回null
 *      3.block的返回值必须是Boolean类型
 */
fun testTakeIf() {
    val value = "test".takeIf {
        it.contains("a")
    }
    println(value)

    //结果：
    //null
}

/**
 * takeUnless
 *      1.it = receiver
 *      2.不满足block中条件，返回receiver，否则返回null
 *      3.block的返回值必须是Boolean类型
 *      4.和 takeIf 用法相反
 */
fun testTakeUnless() {
    val value = "test".takeUnless {
        it.contains("a")
    }
    println(value)

    //结果：
    //test
}

/**
 * use
 *      1.block执行完后，自动执行 Closeable 的 close()
 */
fun testUse() {
    File("").inputStream().reader().buffered().use {
        println(it.readLine())
    }
}
