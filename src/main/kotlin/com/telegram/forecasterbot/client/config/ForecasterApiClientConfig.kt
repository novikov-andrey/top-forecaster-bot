package com.telegram.forecasterbot.client.config

import com.telegram.forecasterbot.client.ForecasterClientApi
import com.telegram.forecasterbot.client.properties.ForecasterApiProperties
import feign.Feign
import feign.Request
import feign.Retryer
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class ForecasterApiClientConfig(
        private val properties: ForecasterApiProperties
) {

    @Bean
    fun forecasterClientApi(): ForecasterClientApi =
            Feign.builder()
                    .retryer(Retryer.NEVER_RETRY)
                    .options(
                            Request.Options(
                                    properties.connectTimeout,
                                    properties.connectionRequestTimeout
                            )
                    )
                    .decoder(JacksonDecoder())
                    .encoder(JacksonEncoder())
                    .logger(Slf4jLogger(ForecasterClientApi::class.java))
                    .target(ForecasterClientApi::class.java, properties.baseUri)

}
