package com.telegram.forecasterbot.service

import com.telegram.forecasterbot.model.User

interface MessageHandler {
    fun handle(message: String, user: User): String
}
