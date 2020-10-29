package com.wayne.kotlin.lambda

fun main() {

    val callJava = CallJava()
    callJava.setCallback(null)
    println()
    callJava.setCallback(object : CallJava.MyCallback {
        override fun callback(str: String) {
            println(str)
        }
    })
    println()

    val callKotlin = CallKotlin()

    //参数直接传入匿名函数
    callKotlin.setCallback(fun(str: String): Unit {
        println(str)
    })
    //匿名函数写成lambda表达式
    callKotlin.setCallback({ str: String ->
        println(str)
    })
    //如果Lambda是函数的最后一个参数，可以把Lambda写在括号的外面
    callKotlin.setCallback() { str: String ->
        println(str)
    }
    //如果Lambda是函数唯一的参数，可以直接把括号去掉
    callKotlin.setCallback { str: String ->
        println(str)
    }
    //如果Lambda是单参数的，它的这个参数也可以省略掉不写，参数默认名字是it
    callKotlin.setCallback {
        println(it)
    }

}

/**
 * kotlin写法
 */
class CallKotlin {

    private lateinit var myCall: (String) -> Unit

    fun setCallback(myCall: (String) -> Unit) {
        this.myCall = myCall
        this.myCall("kotlin callback")
    }

}

/**
 * java写法
 */
class CallJava {

    private var callback: MyCallback? = null

    fun setCallback(callback: MyCallback?) {
        println("1")
        this.callback = callback
        println("2")
        this.callback?.callback("java callback")
        println("3")
    }

    interface MyCallback {
        fun callback(str: String)
    }

}
