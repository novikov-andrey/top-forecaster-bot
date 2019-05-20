package com.telegram.forecasterbot.bot

import com.telegram.forecasterbot.client.ForecasterClient
import com.telegram.forecasterbot.client.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import javax.annotation.PostConstruct

@Component
class TopForecasterBot : TelegramLongPollingBot() {

    @Autowired
    private lateinit var forecasterClient: ForecasterClient

    @PostConstruct
    fun init() {
        TelegramBotsApi().registerBot(this)
    }

    override fun getBotUsername(): String {
       return "@FootballPredictorBot"
    }

    override fun getBotToken(): String {
        return "test"
    }

    override fun onUpdateReceived(update: Update?) {
        val sendMessage = SendMessage().setChatId(update?.message?.chatId)
        if (update?.message?.text == "Привет") {
            sendMessage.text = "Привет!"
            execute(sendMessage)
        }

        if (update?.message?.text == "/register-me") {
            forecasterClient.register(
                    User(
                            telegramId = update.message.from.id.toLong(),
                            chatId = update.message.chatId,
                            name = update.message.from.userName
                    )
            )
            sendMessage.text = "Пользователь @${update.message.from.userName} успешно зарегистрирован!"
            execute(sendMessage)
        }
    }
}
