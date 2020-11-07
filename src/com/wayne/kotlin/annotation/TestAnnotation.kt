package com.wayne.kotlin.annotation

/**
 * 注解
 *      1.注解是对程序附加信息的说明
 *      2.注解可以对类、函数、函数参数、属性等做标注
 *      3.注解的信息可用于源码级、编译期、运行时
 *
 * 注解定义
 *      annotation class Api(val url: String)
 *      注解类构造函数的参数支持类型：基本类型、KClass、枚举、其他注解
 *
 * 限定标注对象
 *      @Target(AnnotationTarget.CLASS)
 *
 * 指定作用时机
 *      @Retention(AnnotationRetention.RUNTIME)
 *      SOURCE < BINARY < RUNTIME
 *
 * 注解的使用
 *      @Api("https://api.github.com")
 *      class GitHubApi{}
 *
 * 明确注解的标注对象
 *      @file:JvmName("Hello") //给文件加注解
 *
 * 注解 VS 注释
 *      注解：Annotations，特定语法现象，参与编译
 *      注释：Comments，只存在与源码中，提升代码可读性
 */
fun main() {

}



