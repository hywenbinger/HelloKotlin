package com.wayne.kotlin.`object`

/**
 * 抽象类
 */
fun main() {
    val son = Son()
    son.hi()
}

class Son : Father() {

    override val name: String
        get() = "son"

    override fun initName() {
        println("我叫你一声你敢答应吗？")
    }

}


abstract class Father {

    fun hi() {
        initName()
        println("name is $name")
    }


    abstract val name: String
    abstract fun initName()

}