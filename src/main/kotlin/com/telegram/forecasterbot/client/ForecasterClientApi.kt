package com.telegram.forecasterbot.client

import com.telegram.forecasterbot.model.User
import feign.Headers
import feign.RequestLine
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@Headers(HttpHeaders.CONTENT_TYPE + ": " + MediaType.APPLICATION_JSON_UTF8_VALUE)
interface ForecasterClientApi {

    @RequestLine("POST /user/registration")
    fun register(user: User)
}
