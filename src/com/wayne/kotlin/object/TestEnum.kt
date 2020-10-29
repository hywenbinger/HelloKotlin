package com.wayne.kotlin.`object`

/**
 * 枚举类
 */
fun main() {
    println(State.OFF.name)
    println(Color.RAD.ordinal)
    println(Color.GREEN.rgb)
    Color.BLUE.print()
}

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

enum class State {
    OFF,
    ON
}