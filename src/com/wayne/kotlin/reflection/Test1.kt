package com.wayne.kotlin.reflection

import com.wayne.kotlin.safeAs
import com.wayne.kotlin.times
import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredFunctions

/**
 * 获取泛型实参
 *
 * 注意：Proguard默认会把Signature去掉，所以要想获取到泛型实参，应该添加配置【-keepattributes Signature】
 */
fun main() {
    getApiFunType()

    println("-" * 38)

    val subType = SubType()
    println(subType.typeParameter)
    println(subType.typeParameterJava)
}

/**
 * 获取函数的返回类型的泛型实参
 *
 * interface Api是不是很类似使用Retrofit时定义的Api接口？
 * 从该示例中，可以了解到Retrofit是怎么获取泛型实参的。
 */
interface Api {
    fun getValues(): List<String>
}

fun getApiFunType() {

    // 使用Kotlin反射API
    Api::class.declaredFunctions.first {
        it.name == "getValues"
    }.returnType.arguments.forEach {
        println(it) // KTypeProjection(variance=INVARIANT, type=kotlin.String)
        println(it.type) // kotlin.String
    }

    // 使用Kotlin反射API
    Api::getValues.returnType.arguments.forEach {
        println(it) // KTypeProjection(variance=INVARIANT, type=kotlin.String)
        println(it.type) // kotlin.String
    }

    // 使用Java反射API
    Api::class.java.getDeclaredMethod("getValues")
        .genericReturnType.safeAs<ParameterizedType>()!!.actualTypeArguments.forEach {
        println(it) // class java.lang.String
    }

    /**
     * 问题：为什么使用Kotlin和Java的Api反射获取到的String类型不一样呢？这里分明用的都是Kotlin中的String。
     *
     * 答案：【Java反射只认Java的类，虽然这里用的是Kotlin.String，但编译后会变成java.lang.String】
     */
}


/**
 * 获取父类的泛型实参
 *
 * 抽象父类中的this，肯定是子类的实例
 */
abstract class SuperType<T> {
    val typeParameter by lazy {
        this::class.supertypes.first().arguments.first().type!!
    }

    val typeParameterJava by lazy {
        this.javaClass.genericSuperclass.safeAs<ParameterizedType>()!!.actualTypeArguments.first()!!
    }
}

class SubType : SuperType<String>()

