package com.telegram.forecasterbot.model

import com.telegram.forecasterbot.model.enums.CommandType

data class Command(
        val commandType: CommandType,
        val content: String
)
