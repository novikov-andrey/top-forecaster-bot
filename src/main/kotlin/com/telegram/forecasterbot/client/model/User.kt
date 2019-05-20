package com.telegram.forecasterbot.client.model

data class User (
        var telegramId: Long,
        var chatId: Long,
        var name: String
)
