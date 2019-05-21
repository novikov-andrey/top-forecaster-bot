package com.telegram.forecasterbot.command

import com.telegram.forecasterbot.exception.CommandNotFoundException
import com.telegram.forecasterbot.model.Command
import com.telegram.forecasterbot.model.enums.CommandType
import mu.KLogging
import org.springframework.stereotype.Component
import java.util.NoSuchElementException

@Component
class CommandBuilder {

    companion object: KLogging() {
        const val SEPARATOR = " "
    }

    fun build(message: String): Command {
        val parts = message.split(SEPARATOR)
        val command = parts[0]
        try {
            val commandType = CommandType.fromName(command)
            val content = parts.drop(1).joinToString(separator = SEPARATOR)
            return Command(commandType, content)
        } catch (e: NoSuchElementException) {
            val errorMessage = "No such command: $command"
            logger.error(e) { errorMessage }
            throw CommandNotFoundException(errorMessage)
        }
    }
}
