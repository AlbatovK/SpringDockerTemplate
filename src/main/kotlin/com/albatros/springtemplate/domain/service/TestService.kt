package com.albatros.springtemplate.domain.service

import com.albatros.springtemplate.domain.model.sbp.SbpRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaResponseModel
import org.springframework.beans.factory.annotation.Autowired
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
            CacheEvict("Sbp", allEntries = true)
        ]
    )
    fun eraseCache() {
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
}
