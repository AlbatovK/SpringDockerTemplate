package com.albatros.springtemplate.domain.service

import com.albatros.springtemplate.domain.model.sbp.SbpRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaResponseModel
import com.albatros.springtemplate.domain.model.webhook.CallbackClientRequest
import com.albatros.springtemplate.domain.model.webhook.CallbackClientResponse
import com.fasterxml.jackson.databind.JsonNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.nio.charset.Charset

@Service
class TestService {

    @Autowired
    private lateinit var webClient: WebClient

    @Caching(
        evict = [
            CacheEvict("Visa", allEntries = true),
            CacheEvict("Sbp", allEntries = true),
            CacheEvict("Test", allEntries = true)
        ]
    )
    fun eraseCache() {
    }

    @Autowired
    private lateinit var manager: CacheManager

    fun getTestServiceRandomStructureResponse(payload: String, tree: JsonNode) {
        val keyValueString = tree
            .fieldNames()
            .asSequence()
            .toList()
            .map { it.trim() }
            .filter {
                !it.equals("id", true) &&
                        !it.contains("cvv", true) &&
                        !it.contains("cvs", true)
                        && !it.contains("description")
            }.joinToString("") { "$it-${tree.findValue(it)}" }

        manager.getCache("Test")?.put(keyValueString, payload)
    }

    @Cacheable(
        value = ["Visa"],
        key = "#visa.params.amount" +
                ".concat(#visa.params.cardNumber).concat(#visa.params.cardHolderName)" +
                ".concat(#visa.params.description).concat(#visa.method)"
    )
    fun getTestServiceVisaResponse(visa: VisaRequestModel) =
        webClient
            .put()
            .uri("/visa")
            .accept(MediaType.APPLICATION_JSON)
            .body(
                Mono.just(visa), VisaRequestModel::class.java
            )
            .retrieve()
            .bodyToMono(VisaResponseModel::class.java)
            .block()

    @Cacheable(
        value = ["Sbp"],
        key = "#sbp.pgCardNumber.concat(#sbp.pgDescription).concat(#sbp.pgCardHolder)"
    )
    fun getTestServiceSbpResponse(id: String, sbp: SbpRequestModel) = webClient
        .post()
        .uri("/master/$id")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
        .acceptCharset(Charset.forName("UTF-8"))
        .body(
            Mono.just(sbp), SbpRequestModel::class.java
        )
        .retrieve()
        .bodyToMono(String::class.java)
        .block()

    @Cacheable(
        value = ["Callback"],
        key = "#callbackClientRequest.amount.value.toString()" +
                ".concat(#callbackClientRequest.amount.currency)" +
                ".concat(#callbackClientRequest.postUrl)"
    )
    fun getTestServiceCallbackResponse(callbackClientRequest: CallbackClientRequest) = webClient
        .post()
        .uri("/webhook")
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
        .body(
            Mono.just(callbackClientRequest), CallbackClientRequest::class.java
        )
        .retrieve()
        .bodyToMono(CallbackClientResponse::class.java)
        .block()
}

