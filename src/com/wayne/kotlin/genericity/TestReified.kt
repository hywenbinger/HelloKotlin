package com.wayne.kotlin.genericity

fun main() {

}

/**
 * 【reified】是为了满足inline特性而设计的语法糖，
 * 因为给函数使用内联之后，编译器会用其函数体来替换掉函数调用，
 * 而如果该函数里面有泛型就可能会出现编译器不懂该泛型的问题，
 * 所以引入reified，使该泛型被智能替换成对应的类型
 */
inline fun <reified T : Any> test() {

}