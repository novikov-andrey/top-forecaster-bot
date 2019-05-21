package com.telegram.forecasterbot.service.impl

import com.telegram.forecasterbot.command.CommandBuilder
import com.telegram.forecasterbot.command.CommandHandler
import com.telegram.forecasterbot.command.HandleCommand
import com.telegram.forecasterbot.exception.CommandNotFoundException
import com.telegram.forecasterbot.model.User
import com.telegram.forecasterbot.service.MessageHandler
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class MessageHandlerImpl(
        private val commandBuilder: CommandBuilder
) : MessageHandler {

    companion object: KLogging() {
        const val COMMAND_PREFIX = "/"
    }

    @Autowired
    private lateinit var context: ApplicationContext

    override fun handle(message: String, user: User): String {
        val command = commandBuilder.build(message)
        val commandHandler = findRequestedBean(command.commandType.commandName)
        return commandHandler.execute(command, user)
    }

    private fun findRequestedBean(command: String): CommandHandler {
        val commandHandlersBeans = context.getBeansWithAnnotation(HandleCommand::class.java).values
        commandHandlersBeans.forEach { commandBean ->
            if (isRequestedCommand(commandBean, command)) {
                return commandBean as CommandHandler
            }
        }
        throw CommandNotFoundException("No such command handler for $command")
                .also {
                    logger.error { "Error! No such command $command" }
                }
    }

    private fun isRequestedCommand(commandBean: Any, command: String) =
            commandBean is CommandHandler
                    && commandBean.javaClass.getAnnotation(HandleCommand::class.java).name == command
}
