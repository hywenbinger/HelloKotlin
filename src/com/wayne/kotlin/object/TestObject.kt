package com.wayne.kotlin.`object`

fun main() {
    val person = Person("wayne", 18)

    /**
     * 属性的引用
     *      1.使用【类名】获取属性引用，修改或获取属性时，需要指明【receiver】
     */
    val nameRef = Person::name
    nameRef.set(person, "wayne han")
    /**
     * 属性的引用
     *      2.使用【类的实例】获取属性引用，修改或获取属性时，不需要指明【receiver】
     */
    val ageRef = person::age
    ageRef.set(21)
}

/**
 * 类的继承
 *      1.Kotlin的类默认是【final】的，继承它的话需要添加【open】关键字
 */
class SimpleClass2(x: Int) : SimpleClass(x),
    SimpleInterface {
    override fun print() {
        super.print()
        println("Simple2 class function")
    }

    override fun go() {
        TODO("not implemented")
    }
}

open class SimpleClass(var x: Int) {
    open fun print() {
        println("Simple class function, x = $x")
    }
}

interface SimpleInterface {
    fun go()
}

/**
 * 类的属性：
 *      1.Kotlin中是【属性property】
 *      2.Java中则是【字段field】
 *      3.【property】=【backing field】+【getter】+【setter】
 */
class Person(name: String, age: Int) {
    var name = name
        get() {
            println("name属性的getter")
            return field
        }
        set(value) {
            println("name属性的setter")
            field = value
        }
    var age = age
}

/**
 * 类的【构造函数】和【init函数】
 */
class Dog constructor(var name: String, msg: String) {
    init {
        println("$name message is $msg")
    }
}

/**
 * Kotlin推荐使用【主构造器 + 默认参数】的形式实现构造函数
 * 【@JvmOverloads】：主构造器默认参数在Java代码中可以以重载的形式调用
 */
class Cat @JvmOverloads constructor(var age: Int, var name: String = "unknown")