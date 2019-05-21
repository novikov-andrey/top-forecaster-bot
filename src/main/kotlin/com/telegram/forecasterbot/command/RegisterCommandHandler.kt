package com.telegram.forecasterbot.command

import com.telegram.forecasterbot.client.ForecasterClient
import com.telegram.forecasterbot.model.Command
import com.telegram.forecasterbot.model.User
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@HandleCommand("/registerme")
class RegisterCommandHandler : CommandHandler {

    companion object: KLogging()

    @Autowired
    private lateinit var forecasterClient: ForecasterClient

    override fun execute(command: Command, user: User): String {
        return try {
            forecasterClient.register(user)
            "Пользователь @${user.name} успешно зарегистрирован!"
        } catch (e: Exception) {
            val errorMessage = "Ошибка регистрации пользовтеля @${user.name}"
            logger.error(e) { errorMessage }
            errorMessage
        }
    }
}
