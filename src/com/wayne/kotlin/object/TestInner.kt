package com.wayne.kotlin.`object`

/**
 * 内部类和嵌套类
 */
fun main() {

    // 内部类初始化
    // 内部类相当于java的非静态内部类，所以只能通过 [外部类的实例] 初始化
    val inner = Outer().Inner()
    inner.display()

    println("------------------------")

    // 嵌套类初始化
    // 嵌套类相当于java的静态内部类，所以能通过 [外部类.的方式] 初始化
    val nest = Outer.Nest()
    nest.display()
}

class Outer {

    private val width = 1000
    fun show() {
        println("外部类方法:$width")
    }

    // kotlin的内部类相当于java的非静态内部类
    inner class Inner {
        private val x = 10
        fun display(){
            println("内部类属性:$x")
            println("外部类属性:$width")
            show() // 调用外部类方法
        }
    }

    // kotlin的嵌套类相当于java的静态内部类
    class Nest{
        private val y = 20
        fun display(){
            println("嵌套类属性:$y")
//            println("外部类属性:$width") // 编译报错，无法调用外部类属性
//            show() // 编译报错，无法调用外部类方法
        }
    }

}