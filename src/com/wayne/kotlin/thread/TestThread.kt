package com.wayne.kotlin.thread

import kotlin.concurrent.thread

/**
 * 线程
 */
fun main() {
    println(Thread.currentThread())

    task()

    thread {
        task()
    }

    thread(name = "TestThread") {
        task()
    }

}

fun task() {
    (1..5).forEach {
        println("第${it}次执行 - ${Thread.currentThread().name}")
        Thread.sleep(1000)
    }
    println("任务执行完，当前线程名${Thread.currentThread().name}")
}