package com.wayne.kotlin.`object`

/**
 * 内部类
 * 嵌套类
 * 匿名内部类
 */
fun main() {


    /**
     * 内部类初始化
     * 内部类相当于java的非静态内部类，所以只能通过【外部类的实例】初始化
     */
    val inner = Outer().Inner()
    inner.display()

    println("------------------------")

    /**
     * 嵌套类初始化
     * 嵌套类相当于java的静态内部类，所以能通过【外部类.的方式】初始化
     */
    val nest = Outer.Nest()
    nest.display()

    /**
     * Kotlin的匿名内部类是可以多继承的
     * 那么它到底是什么类型呢？
     * Runnable & Cloneable ？
     *
     * TypeScript即支持这种【交叉类型】，同时还支持一种【联合类型】(Runnable | Cloneable)
     * Kotlin说不定以后也会支持这种类型吧
     */
    val runnableCloneable = object : Runnable, Cloneable {
        override fun run() {

        }
    }
    println(runnableCloneable)
}

class Outer {

    private val width = 1000
    fun show() {
        println("外部类方法:$width")
    }

    /**
     * 内部类相当于java的【非静态内部类】
     * 添加了【inner】关键字
     */
    inner class Inner {
        private val x = 10
        fun display() {
            println("内部类属性:$x")

            /**
             * 可以调用外部类的属性和方法，因为非静态内部类持有外部类的实例
             */
            println("外部类属性:$width")
            show()
        }
    }

    /**
     * 嵌套类相当于java的【静态内部类】
     * 未添加关键字
     */
    class Nest {
        private val y = 20
        fun display() {
            println("嵌套类属性:$y")

            /**
             * 不能调用外部类的属性和方法
             */
            //println("外部类属性:$width") // 编译报错
            //show() // 编译报错
        }
    }

}