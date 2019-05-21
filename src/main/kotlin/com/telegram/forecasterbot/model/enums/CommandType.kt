package com.telegram.forecasterbot.model.enums

enum class CommandType(val commandName: String) {
    REGISTERME("/registerme");

    companion object {
        private val map = CommandType.values().associateBy(CommandType::commandName)
        fun fromName(commandName: String): CommandType = map.getValue(commandName)
    }
}
