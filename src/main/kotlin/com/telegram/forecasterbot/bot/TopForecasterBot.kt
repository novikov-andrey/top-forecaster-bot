package com.telegram.forecasterbot.bot

import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TopForecasterBot : TelegramLongPollingBot() {

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
    }
}
