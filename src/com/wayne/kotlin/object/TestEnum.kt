package com.wayne.kotlin.`object`

/**
 * 枚举类
 */
fun main() {
    println(State.OFF.name)
    val state = State.ON
    println("$state next is ${state.next()}")

    println("-------------------------------------------")

    println(Color.RAD.ordinal)
    println(Color.GREEN.rgb)
    Color.BLUE.print()
}

/**
 * 枚举定义属性和抽象方法
 */
enum class Color(var rgb: Int) {
    RAD(0xff0000) {
        override fun print() {
            println("红色")
        }
    },
    GREEN(0x00ff00) {
        override fun print() {
            println("绿色")
        }
    },
    BLUE(0x0000ff) {
        override fun print() {
            println("蓝色")
        }
    };

    abstract fun print()
}

/**
 * 创建枚举类
 */
enum class State {
    OFF,
    ON
}

/**
 * 枚举定义扩展方法
 */
fun State.next(): State {
    return State.values().let {
        val nextOrdinal = (this.ordinal + 1) % it.size
        it[nextOrdinal]
    }
}