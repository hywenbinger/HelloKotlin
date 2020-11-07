@file:JvmName("KotlinAnnotation")
@file:JvmMultifileClass

package com.wayne.kotlin.annotation

import java.io.IOException
import kotlin.jvm.Throws

/**
 * 常见内置注解的使用
 *      1.kotlin.annotation.*：用于标注注解的注解
 *          * Target
 *          * Retention
 *          * Repeatable
 *          * MustBeDocumented
 *      2.kotlin.*：标准库的一些通用用途的注解
 *          * Metadata：Kotlin的反射信息通过该注解附带在元素上
 *          * UnsafeVariance：泛型用来破除型变限制
 *          * Suppress：用来去除编译器警告，警告类型作为参数传入
 *      3.kotlin.jvm.*：用于与JVM交互的注解
 *          * JvmField：生成Java Field
 *          * JvmName：指定类、函数等生成的JVM名字
 *          * JvmMultifileClass：处理相同Java类名
 *          * JvmOverloads：函数默认参数生成函数重载
 *          * JvmStatic：生成静态成员
 *          * Synchronized：标记同步函数
 *          * Volatile：生成volatile的Field
 *          * Throws：标记函数抛出的异常类型
 */
fun main() {

}

@Volatile
var volatileProperty: Int = 0

@Synchronized
fun syncFun() {
}

val lock = Any()
fun syncBlock() {
    synchronized(lock) {

    }
}

@Throws(IOException::class)
fun throwExceptionFun() {
}
