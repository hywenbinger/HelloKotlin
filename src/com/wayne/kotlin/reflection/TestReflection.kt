package com.wayne.kotlin.reflection

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.staticFunctions
import kotlin.reflect.typeOf

/**
 * 反射
 *
 * 反射常用的数据结构：
 *      1.KType：描述未擦除的类型或泛型参数，例如Map<String, Int>；可通过typeOf或者以下类型获取对应的父类、属性、函数等；对应Java的Type
 *      2.KClass：描述对象的实际类型，不包括泛型参数，例如Map；可通过对象、类型名直接获取；对应Java的Class
 *      3.KProperty：描述属性，可通过属性引用、属性所在类的KClass获取；约对应Java的Field
 *      4.KFunction：描述函数，通过函数引用、函数所在类的KClass获取；约对应Java的Method
 *
 * 反射的使用：
 *      1.使用Java反射
 *          优点：无需引入额外的依赖，首次使用速度相对较快
 *          缺点：无法访问Kotlin语法特性，需对Kotlin生成的字节码足够了解
 *      2.使用Kotlin反射
 *          优点：支持访问Kotlin几乎所有特性，API设计更友好
 *          缺点：需引入Kotlin反射库(2.5MB，编译后400KB)(implementation:'org.jetbranis.kotlin:kotlin-reflect')，首次调用慢
 *
 * 为什么使用Kotlin反射API首次调用慢呢(慢一两个数量级)？
 * 因为Kotlin的反射信息是写在metadata注解中的(反编译后可查看)，首次反射会反序列化注解中的信息，所以会慢。
 */
fun main() {
    testBasic()
}

fun testBasic() {
    // KClass<String>类型
    // 打印结果：class kotlin.String
    println(String::class)

    // Class<String>类型
    // 打印结果：class java.lang.String
    println(String::class.java)

    // KClass<String>类型
    // 打印结果：class kotlin.String
    println(String::class.java.kotlin)
}

/**
 * 熟悉KType相关API
 */
fun testType() {
    val mapCls = Map::class // KClass一定对应的是【擦除后的类型】
    println(mapCls) // 打印结果：class kotlin.collections.Map

    val mapType = typeOf<Map<in String, Int>>() // KType则对应【擦除前的类型】
    println(mapType) // 打印结果：kotlin.collections.Map<in kotlin.String, kotlin.Int>
    mapType.arguments.forEach {
        println(it) // 可获取【泛型型变类型】和【实参类型】
        // KTypeProjection(variance=IN, type=kotlin.String)
        // KTypeProjection(variance=INVARIANT, type=kotlin.Int)
    }
}

/**
 * 熟悉Kotlin反射API
 */
fun reflectionModelKotlin() {
    val cls = ModelKotlinClass::class
    println("类名：${cls.simpleName}") // ModelKotlin
    println("类全名：${cls.qualifiedName}") // com.wayne.kotlin.reflection.ModelKotlin


    println(cls.declaredMemberFunctions) // 获取类中的函数，注意扩展函数是拿不到的
    println(cls.declaredMemberExtensionFunctions) // 获取类中的扩展函数，一般都是别的类的扩展函数
    println(cls.declaredFunctions) // 获取类中的所有的函数

    println(ModelJava::class.staticFunctions) // 获取类中的所有静态函数

    println("所有成员：") // 获取定义在类中的所有属性、函数、扩展函数
    cls.members.forEach {
        println(it)
    }
}

fun ModelKotlinClass.outerFun() {}

/**
 * 一个普通的Kotlin类
 */
class ModelKotlinClass {
    var age: Int = 18
    var name: String = "wayne"
    fun myFun() {
        println("age is $age, name is $name")
    }

    companion object {
        fun myStatic() {
            println("static fun")
        }
    }

    fun String.hello() {
        println("String say hello")
    }

}

/**
 * 一个普通的Kotlin object类
 */
object ModelKotlinObject {
    fun testStatic() {}
}

