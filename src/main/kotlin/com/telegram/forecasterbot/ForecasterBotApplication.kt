package com.telegram.forecasterbot

import com.telegram.forecasterbot.bot.TopForecasterBot
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import javax.annotation.PostConstruct

@SpringBootApplication
class ForecasterBotApplication {

    @PostConstruct
    fun initBot() {
        ApiContextInitializer.init()
        TelegramBotsApi().registerBot(TopForecasterBot())
    }
}

fun main(args: Array<String>) {
    runApplication<ForecasterBotApplication>(*args)
}

