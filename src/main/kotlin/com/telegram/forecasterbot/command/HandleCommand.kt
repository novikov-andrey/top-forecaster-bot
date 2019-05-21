package com.telegram.forecasterbot.command

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class HandleCommand (
        val name: String
)
