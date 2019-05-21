package com.telegram.forecasterbot.command

import com.telegram.forecasterbot.model.Command
import com.telegram.forecasterbot.model.User

interface CommandHandler {
    fun execute(command: Command, user: User): String
}
