package com.wayne.kotlin.`fun`

import java.io.File

/**
 * 统计字符的个数
 * 体验函数式编程的乐趣
 */
fun main() {
    File("HelloKotlin.iml")
        .readText() //读文本
        .toCharArray() //转换成字符数组
        .filterNot {
            it.isWhitespace() //过滤空白字符
        }.groupBy {
            it //分组
        }.map {
            it.key to it.value.size //映射
        }.let {
            println(it)
        }
}