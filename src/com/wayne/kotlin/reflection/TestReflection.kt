package com.wayne.kotlin.reflection

import kotlin.reflect.KFunction
import kotlin.reflect.KProperty

/**
 * 反射
 */
fun main() {

    val s1 = String::class.java
    println("Java String类全名：$s1")

    println("----------------------------------")

    val r1 = Model::class
    println("类名：${r1.simpleName}")
    println("类全名：${r1.qualifiedName}")
    println("isCompanion：${r1.isCompanion}")
    println("isData：${r1.isData}")
    println("isOpen：${r1.isOpen}")
    println("isSealed：${r1.isSealed}")
    println("isFinal：${r1.isFinal}")

//    var sBuilder = StringBuilder()
//    r1.members.joinTo(sBuilder, ", ", "[", "]")
//    println("所有成员：${sBuilder.toString()}")
    println("所有成员：")
    r1.members.forEach {
        println(it)
    }

//    sBuilder = StringBuilder()
//    r1.members.filter { it is KFunction }.joinTo(sBuilder, ", ", "[", "]")
//    println("所有属性：${sBuilder.toString()}")
    println("所有属性：")
    r1.members.filter {
        it is KProperty
    }.forEach {
        println(it)
    }

    println("所有方法：")
    r1.members.filter {
        it is KFunction
    }.forEach {
        println(it)
    }
}