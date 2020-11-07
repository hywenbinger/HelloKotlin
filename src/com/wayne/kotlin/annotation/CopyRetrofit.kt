package com.wayne.kotlin.annotation


/**
 * 仿 Retrofit 反射读取注解请求网络
 */
fun main() {

}

data class User(var login: String, var location: String)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Api(val url: String)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class Get(val url: String = "")

@Api("https://api.github.com")
interface GitHubApi {

    @Api("users")
    interface Users {

        @Get("{name}")
        fun get(name: String): User
    }
}