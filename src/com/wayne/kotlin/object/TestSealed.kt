package com.wayne.kotlin.`object`

/**
 * 密封类
 */
fun main() {
    val person1 = SealedExpr.Person(18, "a")
    println(person1)
    val person2 = SealedExpr.Person(20, "b")
    println(person2)

    val add = SealedExpr.Add.add(10, 10)
    println(add)
}

private fun SealedExpr.Add.add(a: Int, b: Int): Int {
    return a + b
}

sealed class SealedExpr {
    data class Person(var age: Int, var name: String) : SealedExpr()
    object Add : SealedExpr()
}