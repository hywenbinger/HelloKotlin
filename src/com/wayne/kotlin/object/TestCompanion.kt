package com.wayne.kotlin.`object`

/**
 * 伴生对象 和 对象声明
 */
fun main() {

    val age = TestObject.getAge()

    val name = TestCompanion.getName()
    TestCompanion.age

}

class TestCompanion {

    /**
     * 类似Java静态方法
     */
    companion object MyCompanion {
        fun getName() = "wayne"
        const val age: Int = 18
    }

}

/**
 * 类似Java单例类
 */
object TestObject {
    fun getAge() = 18
}