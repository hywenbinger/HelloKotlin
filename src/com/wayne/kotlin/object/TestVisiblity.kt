package com.wayne.kotlin.`object`

/**
 * 类和成员的可见性
 *      1.可见性对比
 *          可见性类型           Java                Kotlin
 *          public              公开                公开，默认
 *          internal            ×                   模块内可见
 *          default             包内可见，默认        ×
 *          protected           包内或子类可见       类内或子类可见
 *          private             类内可见            类或文件内可见
 *      2.internal VS default
 *          * Kotlin中无default
 *          * 一般由SDK或公共组件开发者用于隐藏模块内部细节实现
 *          * default可通过外部创建相同包名来访问，访问控制非常弱
 *          * default会导致不同抽象层次的类聚集到相同包下
 *          * internal可方便处理内外隔离，提升模块代码内聚，减少接口暴露
 *          * internal修饰的Kotlin类或成员在Java中可直接访问
 *      3.顶级声明的可见性
 *          * 顶级声明指文件内直接定义的属性、函数、类等
 *          * 顶级声明不支持protected
 *          * 顶级声明被private修饰表示文件内部可见
 */
fun main() {

}