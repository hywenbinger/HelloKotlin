package com.wayne.kotlin.reflection

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty0
import kotlin.reflect.jvm.isAccessible

/**
 * 可释放对象引用的不可空类型
 *
 * 知识点：反射、属性代理、扩展属性、扩展函数
 */
fun main() {
    val activity = Activity()
    activity.onCreate()
    activity.onDestroy()
}

/**
 * 代理类
 */
class ReleasableNotNull<T : Any> : ReadWriteProperty<Any, T> {

    private var value: T? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return this.value ?: throw IllegalStateException("Not initialized or released already.")
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this.value = value
    }

    fun isInitialized() = this.value != null

    fun release() {
        this.value = null
    }

}

inline val KProperty0<*>.isInitialized: Boolean
    get() {
        isAccessible = true
        return (this.getDelegate() as? ReleasableNotNull<*>)?.isInitialized()
            ?: throw IllegalAccessException("Delegate is not an instance of ReleasableNotNull or is null.")
    }

/**
 * 【KProperty0】是什么？
 * 【0】代表receiver
 */
fun KProperty0<*>.release() {
    isAccessible = true
    (this.getDelegate() as? ReleasableNotNull<*>)?.release()
        ?: throw IllegalAccessException("Delegate is not an instance of ReleasableNotNull or is null.")
}

/**
 * 测试用例
 *
 * 模拟Android中Bitmap的创建和回收
 */
class Activity {

    private var bitmap by ReleasableNotNull<Bitmap>()

    fun onCreate() {
        println(::bitmap.isInitialized)
        bitmap = Bitmap(1920, 1080)
        println(::bitmap.isInitialized)
    }

    fun onDestroy() {
        println(::bitmap.isInitialized)
        ::bitmap.release()
        println(::bitmap.isInitialized)
    }
}

class Bitmap(val width: Int, val height: Int)