package com.wayne.kotlin.`object`

/**
 *                  JavaBean                          DataClass
 * 构造方法          默认无参构造                       属性作为参数
 * 字段             字段私有，getter/setter公开         属性
 * 继承性           可继承和被继承                      不可被继承
 * component       无                                 相等性、解构
 */
fun main() {
    val data1 = MyData(age = 20, name = "wayne")
    println(data1)

    val data2 = data1.copy(name = "luna", female = true)
    println(data2)

    /**
     * 数据类的解构
     */
    val (age, name, female) = data1
    println("$name age is $age, is female $female")

    /**
     * 自定义实现类的解构
     */
    val (value1, value2) = MyComponent(100, 200)
    println("$value1, $value2")
}

/**
 * 数据类
 *
 * 使用【data】关键字修饰
 *
 * 定义在主构造器中的属性，又称为【component】，比如下面的数据类：
 *      var age = myData.component1()
 *      var name = myData.component2()
 *      var female = myData.component3()
 * 编译器基于 component 自动生成了【equals/hashCode/toString/copy】方法
 *
 * 数据类不能被继承，为什么呢？
 * 因为会违反【对称性】和【传递性】
 * 注意：在Kotlin1.1之前也不能继承别的类
 *
 * DataClass = JavaBean + Component + final
 *
 * 如何合理使用数据类型：
 *      1.大多情况下不需要额外的实现
 *      2.属性类型最好是基本类型、String、其他数据类
 *      3.Component限制，不能自定义getter和setter
 *      4.属性最好不可变，定义为val
 */
data class MyData(val age: Int = 18, val name: String = "unknown", val female: Boolean = false)


/**
 * 解构
 *
 * 自定义的componentN()，需要注意：
 *      1.componentN()需要标记为【operator】, 才可以在解构声明中使用
 *      2.componentN()的返回值类型必须与属性类型一致
 */
class MyComponent(var value1: Int, var value2: Int) {
    operator fun component1(): Int {
        return value1
    }

    operator fun component2(): Int {
        return value2
    }
}