package com.telegram.forecasterbot.bot

import com.telegram.forecasterbot.exception.CommandNotFoundException
import com.telegram.forecasterbot.model.User
import com.telegram.forecasterbot.service.MessageHandler
import com.telegram.forecasterbot.service.impl.MessageHandlerImpl
import mu.KLogging
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import javax.annotation.PostConstruct

@Component
class TopForecasterBot(
        private val messageHandler: MessageHandler
) : TelegramLongPollingBot() {

    companion object: KLogging()

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
        val message = update?.message?.text!!
        if (!checkIsCommand(message)) return

        val user = User(
                telegramId = update.message.from.id.toLong(),
                chatId = update.message.chatId,
                name = update.message.from.userName
        )

        val response = try {
            messageHandler.handle(message, user)
        } catch (e: CommandNotFoundException) {
            logger.error(e) { "Fail to execute command" }
            "Некорректная команда"
        } catch (e: Exception) {
            logger.error(e) { "Unexpected error occurred" }
            "Ошибка выполнения комманды"
        }
        sendMessage(user.chatId, response)
    }

    private fun sendMessage(chatId: Long, text: String) {
        execute(SendMessage(chatId, text))
    }

    private fun checkIsCommand(message: String) =
            message.startsWith(MessageHandlerImpl.COMMAND_PREFIX)
}
