package com.telegram.forecasterbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
class ForecasterBotApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    runApplication<ForecasterBotApplication>(*args)
}

