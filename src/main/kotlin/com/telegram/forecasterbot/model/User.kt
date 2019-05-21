package com.telegram.forecasterbot.model

data class User (
        var telegramId: Long,
        var chatId: Long,
        var name: String
)
