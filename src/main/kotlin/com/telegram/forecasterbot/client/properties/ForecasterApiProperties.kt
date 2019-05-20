package com.telegram.forecasterbot.client.properties

import feign.Logger
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(prefix = "forecaster-api.client")
class ForecasterApiProperties {
    open lateinit var baseUri: String
    open var connectTimeout: Int = 0
    open var connectionRequestTimeout: Int = 0
    open var socketTimeout: Int = 0
    open var logLevel = Logger.Level.NONE
}
