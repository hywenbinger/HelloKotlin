package com.wayne.kotlin.`object`

/**
 * 伴生对象 和 对象声明
 */
fun main() {

    TestObject.getAge()
    TestObject.x = 200
    TestObject.y = 200
    //java代码访问方式：
    //TestObject.INSTANCE.getAge()
    //TestObject.setX(200)
    //TestObject.y = 200

    TestCompanion.getName()
    TestCompanion.age

}

class TestCompanion {

    /**
     * 类似Java静态方法
     */
    companion object MyCompanion {
        fun getName() = "wayne"
        var age: Int = 18
    }

}

/**
 * 类似Java单例类
 *
 * 等价与以下代码，饿汉式的单例：
 *  public final class TestObject {
 *      public static final TestObject INSTANCE = new TestObject();
 *  }
 *
 * 使用注解【@JvmStatic】可定义供Java使用的静态成员
 *
 * 思考：为什么Kotlin中没有静态的概念呢？
 * 原因：Kotlin是一门跨平台的语言，不能因为Java平台有静态，Kotlin就定义静态；
 *      C或JS平台本身也没有静态的概念，所以如果Kotlin定义了静态，那么就不太容易实现跨平台了。
 *
 * 使用注解【@JvmField】可定义不生成getter/setter的静态变量
 */
object TestObject {
    fun getAge() = 18
    @JvmStatic
    var x: Int = 100
    @JvmField
    var y: Int = 100;
}