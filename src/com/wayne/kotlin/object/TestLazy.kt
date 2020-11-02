package com.wayne.kotlin.`object`

/**
 * 类属性的延迟初始化
 */
fun main() {

}


class TestLazy {

    /**
     * 方法一：
     *      1.使用【Any?】
     *      2.完全等价Java代码
     * 推荐指数：※
     * 理由：
     *      1.增加代码复杂度
     *      2.初始化与声明分离
     *      3.调用处需要做判空处理
     */
    private var string1: String? = null

    fun initString1() {
        string1 = "unknown"
        println(string1?.length)
    }

    /**
     * 方法二：
     *      1.使用【lateinit var】
     *      2.lateinit会让编译器忽略变量的初始化，不支持Int等基本类型
     *      3.开发者必须能够在完全确定变量值的生命周期下使用lateinit
     *      4.不要在复杂的逻辑中使用lateinit，它只会让你的代码更加脆弱
     *      5.Kotlin1.2中加入的判断lateinit是否初始化的API最好不要用
     * 推荐指数：※※
     * 理由：
     *      1.初始化与声明分离
     *      2.调用处虽无需判空处理，但潜在的初始化问题可能被覆盖
     */
    private lateinit var string2: String

    fun initString2() {
        string2 = "unknown"
        if (::string2.isInitialized) { //判断lateinit属性是否初始化
            println(string2.length)
        }
    }

    /**
     * 方法三：
     *      1.使用【lazy】
     *      2.只有当属性在首次被访问时执行
     * 推荐指数：※※※※※
     * 理由：
     *      1.初始化与声明内聚
     *      2.无需声明可空类型
     */
    private val string3: String by lazy {
        "unknown"
    }

}