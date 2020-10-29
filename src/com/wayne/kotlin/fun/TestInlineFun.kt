package com.wayne.kotlin.`fun`

/**
 * inline
 *      1.【修饰函数】
 *      2.通过内联【函数内容直接复制到调用处】的方式来编译函数
 *      3.作用是【减少函数类型对象的创建】
 *      4.使用inline函数也会使虚拟机栈中栈帧少一层，但这个优化对整体性能优化不明显
 *      5.inline还有一个很妙的用法(用偷天换日的方式，去掉了Java静态方法的前缀)，参照MathJVM.kt
 * noinline
 *      1.【修饰函数类型参数】
 *      2.局部关掉inline的优化
 *      3.摆脱inline带来的【不能把函数类型的参数当对象使用】的限制
 * crossinline
 *      1.【修饰函数类型参数】
 *      2.让【内联函数的函数类型的参数可以被间接调用】，也就是禁止【non-local return】
 *      3.代价是不能在 Lambda 表达式里使用 return
 *
 */
fun main() {

    testInline {
        println("test inline fun")
    }

    val funPostAction = testNoinline({
        println("test noinline fun, 前置操作")
    }, {
        println("test noinline fun, 后续操作")
    })
    println(funPostAction.toString())
    funPostAction()

    testCrossinline {
        println("test crossinline fun")
//        return
        // 由于testCrossinline()是inline函数，所以代码编译优化内联后，return肯定会直接退出main()函数
        // 注：Lambda可以使用 return@label 方式显示指定返回的位置
        // 但是 在为函数参数加上crossinline修饰后，Lambda中就不能使用return了
    }

    println("end")
}

/**
 * 测试inline
 */
inline fun testInline(predict: () -> Unit) {
    predict()
}

/**
 * 测试noinline
 */
inline fun testNoinline(preAction: () -> Unit, noinline postAction: () -> Unit): () -> Unit {
    preAction()
    println("----------华丽的分割线----------")
    postAction()
    return postAction
}

/**
 * 测试crossinline
 *
 * Kotlin规定：Lambda中不允许使用return，除非这个Lambda是inline函数的参数
 *             但是crossinline修饰函数参数，就不再享有上面说的福利了
 */
inline fun testCrossinline(crossinline postAction: () -> Unit) {
    println("hello")
    run {
        postAction()
    }
}

/**
 * 内联属性
 */
class Person {
    var alias: String = ""
    var name
        inline get() = alias
        inline set(value) {
            alias = value
        }
}