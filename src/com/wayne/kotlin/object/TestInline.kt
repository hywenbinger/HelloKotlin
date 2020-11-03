package com.wayne.kotlin.`object`

/**
 * 内联类
 *      1.内联类是对某一个类型的包装
 *      2.类似Java装箱类型
 *      3.编译器会尽可能的使用被包装的类型进行优化
 *      4.在1.3开始进行公测，谨慎使用
 *
 * 内联类的限制
 *      1.主构造器必须有且仅有一个只读val属性
 *      2.不能有backing field的属性
 *      3.被包装的类型不能是泛型类型
 *      4.不能继承父类也不能被继承，但可以实现接口
 *      5.不能定义为其他类的内部类
 *
 * 内联类的使用场景
 *      1.官方的【无符号类型】都是使用内联类，对【有符号类型进行包装】实现的
 *      2.内联类模拟枚举
 *
 * typealias VS inline class
 *      1.类型：
 *          typealias：没有新类型
 *          inline class：有包装类型产生
 *      2.实例：
 *          typealias：与原类型一致
 *          inline class：必要时使用包装类型
 *      3.场景：
 *          typealias：类型更直观
 *          inline class：优化包装类型性能
 */
fun main() {
    var boxInt = BoxInt(5)
    println(boxInt)

    val newBoxInt = boxInt.value * 200
    println(newBoxInt)

    boxInt++
    println(boxInt)
}

/**
 * 创建内联类
 *
 * 下面代码是对Int类型的包装
 */
inline class BoxInt(val value: Int) : Comparable<Int> {

    operator fun inc(): BoxInt {
        return BoxInt(value + 1)
    }

    override fun compareTo(other: Int): Int = value.compareTo(other)
}

/**
 * 使用内联类解决 IntDef 在Kotlin中不生效的问题，性能相对枚举更好，传值也更安全
 */
inline class RouteTypeInline(val value: Int)

object RouteTypes {
    val WALK = RouteTypeInline(0)
    val BUS = RouteTypeInline(1)
    val CAR = RouteTypeInline(2)
}

fun setRouteType(routeTypeInline: RouteTypeInline) {

}