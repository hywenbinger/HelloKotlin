package com.wayne.kotlin.`object`

/**
 * 密封类
 *      1.密封类是一种特殊的抽象类
 *      2.密封类及其子类必须在同一个文件中
 *
 * 密封类 VS 枚举类
 *      1.状态实现：
 *          密封类：子类继承
 *          枚举类：类实例化
 *      2.状态可数：
 *          密封类：子类可数
 *          枚举类：实例可数
 *      3.状态差异：
 *          密封类：类型差异
 *          枚举类：值差异
 *
 * 注意：在Kotlin1.1之前，密封类的子类必须是密封类的内部类
 */
fun main() {
    val player = Player()
    player.play("Happy New Year")
}

/**
 * 创建密封类
 */
sealed class PlayState

/**
 * 密封类的子类
 */
object Idle : PlayState()

/**
 * 密封类的子类
 */
class Playing(val songName: String) : PlayState() {
    fun start() {}
    fun stop() {}
}

/**
 * 密封类的子类
 */
class Error(var errorMsg: String) : PlayState() {
    fun recover() {}
}

/**
 * 密封类测试
 */
class Player {
    var state: PlayState = Idle
    fun play(songName: String) {
        this.state = when (val ss = this.state) {
            Idle -> {
                Playing(songName).also {
                    it.start()
                }
            }
            is Playing -> {
                ss.stop()
                Playing(songName).also {
                    it.start()
                }
            }
            is Error -> {
                ss.recover()
                Playing(songName).also {
                    it.start()
                }
            }
        }
    }
}
