package com.albatros.springtemplate.configuration

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.xml.Jaxb2XmlDecoder
import org.springframework.http.codec.xml.Jaxb2XmlEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.util.concurrent.TimeUnit

@Configuration
class WebClientConfiguration {

    @Value("\${proxy-server.url}")
    private var baseUrl = ""

    companion object {
        const val TIMEOUT = 10000L
    }


    @Bean
    fun webClientWithTimeOut() = WebClient.builder()
        .exchangeStrategies(
            ExchangeStrategies.builder().codecs {
                it.defaultCodecs().jaxb2Decoder(Jaxb2XmlDecoder())
                it.defaultCodecs().jaxb2Encoder(Jaxb2XmlEncoder())
            }.build()
        )
        .clientConnector(
            ReactorClientHttpConnector(
                HttpClient.create()
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT.toInt())
                    .doOnConnected {
                        it.addHandlerLast(
                            ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)
                        )
                        it.addHandlerLast(
                            WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS)
                        )
                    }
            )
        )
        .baseUrl(baseUrl)
        .build()
}
