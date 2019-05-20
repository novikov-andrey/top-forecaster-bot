package com.telegram.forecasterbot.client

import com.telegram.forecasterbot.client.model.User
import mu.KLogging
import org.springframework.stereotype.Component


@Component
class ForecasterClient(
        private val forecasterClientApi: ForecasterClientApi
) {

    companion object: KLogging()

    fun register(user: User) {
        forecasterClientApi.register(user)
    }
}
