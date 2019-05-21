package com.telegram.forecasterbot.client

import com.telegram.forecasterbot.model.User
import mu.KLogging
import org.springframework.stereotype.Component


@Component
class ForecasterClient(
        private val forecasterClientApi: ForecasterClientApi
) {

    companion object: KLogging()

    fun register(user: User) {
        try {
            forecasterClientApi.register(user)
        } catch (e: Exception) {
            logger.error(e) { "Fail to register user with id = ${user.telegramId}" }
            throw e
        }
    }
}
