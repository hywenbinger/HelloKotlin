package com.wayne.kotlin.`object`

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 代理：我【代】替你，处【理】他
 *      1.接口代理：对象【x】代替当前类【A】实现接口【B】的方法
 *      2.属性代理：对象【x】代替属性【a】实现【getter/setter】方法
 *
 */
fun main() {
    val superArray = SuperArray<String>()
    superArray += "Hello"
    superArray["Hello"] = "World"
    superArray[2] = "Kotlin"
    println(superArray.toString())

    val d = D("Wayne Han")
    println(d.firstName)
    d.age = 18
    println(d.age)
    println(d.address)
    d.address = "China"
    println(d.address)
    println(d.msg)
    d.msg = "He is a student"
    println(d.msg)
}

/**
 * -----------------------------------------接口代理---------------------------------------------
 */
interface Api {
    fun a()
    fun b()
}

/**
 * 接口的实现类
 */
class ApiImpl : Api {
    override fun a() {}
    override fun b() {}
}

/**
 * 【传统写法】的接口的包装类
 * 目的：对Api进行功能增强，可能只是修改其中一个方法，但却要实现所有方法
 * 思考：有没有更好的实现方式呢？
 * 答案：使用接口代理
 */
class ApiWrapperLegacy(private val api: Api) : Api {
    override fun a() {
        api.a()
    }

    override fun b() {
        println("call function b")
        api.b()
    }

}

/**
 * 【使用代理】的接口的包装类
 * 对象【api】代替当前类【ApiWrapperDelegate】实现接口【Api】的方法
 */
class ApiWrapperDelegate(private val api: Api) : Api by api {
    override fun b() {
        println("call function b")
        api.b()
    }
}

/**
 * 接口代理的例子
 */
class SuperArray<E>(
    private val list: MutableList<E?> = ArrayList(),
    private val map: MutableMap<Any, E> = HashMap()
) : MutableList<E?> by list, MutableMap<Any, E> by map {
    override fun isEmpty(): Boolean {
        return list.isEmpty() && map.isEmpty()
    }

    override val size: Int
        get() = list.size + map.size

    override fun clear() {
        list.clear()
        map.clear()
    }

    override fun set(index: Int, element: E?): E? {
        if (list.size <= index) {
            repeat(index - list.size + 1) {
                list.add(null)
            }
        }
        return list.set(index, element)
    }

    override fun toString(): String {
        return "list = ${list.toString()}, map = ${map.toString()}"
    }

}

/**
 * -----------------------------------------属性代理---------------------------------------------
 */
class D(private val name: String) {
    /**
     * 属性代理：lazy
     * 【接口Lazy实例】代理了属性【firstName】的【getter】
     */
    val firstName by lazy {
        name.split(" ")[0]
    }

    /**
     * 属性代理：observable
     * 【ObservableProperty的实例】代理了属性【age】的【getter/setter】
     */
    var age by Delegates.observable(0) { property, oldValue, newValue ->
        println("age change from $oldValue to $newValue")
    }

    /**
     * 属性代理：vetoable
     * 与observable基本类似，但不同的是onChange回调会返回一个Boolean值，来决定此次属性值是否执行修改
     */
    var address by Delegates.vetoable("Beijing") { property, oldValue, newValue ->
        println("address change from $oldValue to $newValue")
        return@vetoable newValue == "Beijing"
    }

    /**
     * 属性代理：自定义代理
     */
    var msg by MyDelegate()

}

class MyDelegate {

    operator fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("MyDelegate getValue $property")
        return "msg"
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("MyDelegate setValue $value")
    }
}