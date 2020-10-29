package com.wayne.kotlin.lambda

/**
 * SAM转换
 *      1.Single Abstract Method
 *      2.必须是接口
 *      3.只能有一个方法
 *
 * Java：
 *      一个参数类型为【只有一个方法的接口】的方法，调用时可用Lambda表达式做转换作为参数
 *      所以Java的Lambda表达式是假的，其实是一个SAM接口
 *
 * Kotlin：
 *      一个参数类型为【只有一个方法的Java接口】的Java方法，调用时可用Lambda表达式做转换作为参数
 *      这里规定只能是Java接口，原因是：Kotlin本来就支持函数类型，所以根本没必要使用【只有一个方法的Kotlin接口】
 */
fun main() {

    val eventManager = EventManager()

    // 1
    eventManager.addEventListener {
        println("event is $it")
    }
    eventManager.removeEventListener {
        // addEventListener()中传的是Lambda表达式
        // 那么removeEventListener()中怎么移除呢？？？
    }

    // 2
    eventManager.addEventListener(object : EventManager.OnEventListener {
        override fun onEvent(event: Int) {
            println("event is $event")
        }
    })
    // 上面 1 和 2 的写法是一样的，显然这种写法是肯定不行的；
    // add后，怎么remove呢？remove再创建一个匿名内部类？显然不行。

    // 3
    val onEvent = { event: Int ->
        println("event is $event")
    }
    eventManager.addEventListener(onEvent)
    eventManager.removeEventListener(onEvent)
    // 通过上面 3 的写法add和remove，可以吗？
    // 很显然还是不行，无非是对 1 和 2 换了种写法，本质还是一样的。

    // 4
    val onEventManager = object : EventManager.OnEventListener {
        override fun onEvent(event: Int) {
            println("event is $event")
        }
    }
    eventManager.addEventListener(onEventManager)
    eventManager.removeEventListener(onEventManager)
    // 注意：4 的写法是正确的
    // 因为：onEventManager的类型就是EventManager.OnEventListener，所以add和remove时，就不会进行SAM转换了

    // 5
    val onEventManager1 = EventManager.OnEventListener { event ->
        println("event is $event")
    }
    eventManager.addEventListener(onEventManager1)
    eventManager.removeEventListener(onEventManager1)
    // 5 是 4 的简化写法
}

