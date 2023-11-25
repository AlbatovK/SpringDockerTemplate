package com.albatros.springtemplate.presentation

import com.albatros.springtemplate.domain.model.sbp.SbpRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaRequestModel
import com.albatros.springtemplate.domain.model.visa.VisaResponseModel
import com.albatros.springtemplate.domain.model.webhook.CallbackClientRequest
import com.albatros.springtemplate.domain.model.webhook.CallbackClientResponse
import com.albatros.springtemplate.domain.service.TestService
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.*


@RestController()
class RestController {

    @Autowired
    lateinit var test: TestService

    @PutMapping(value = ["/visa"], consumes = ["application/json"])
    fun interceptVisaTest(@RequestBody visa: VisaRequestModel): VisaResponseModel? {
        return test.getTestServiceVisaResponse(visa)?.apply {
            id = visa.id
        }
    }

    @PostMapping(
        value = ["/master/{id}"],
        consumes = [MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE],
        produces = [MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE]
    )
    fun interceptSbpTest(@PathVariable id: String, @RequestBody request: SbpRequestModel): String? {
        return test.getTestServiceSbpResponse(id, request)
    }

    @DeleteMapping(
        value = ["/"]
    )
    fun eraseCache() = test.eraseCache()

    @Autowired
    private lateinit var cacheManager: CacheManager

    @PostMapping(value = ["/test"])
    fun acceptRandomTestStructure(
        @RequestBody payload: String,
        @RequestHeader headers: HttpHeaders
    ): ResponseEntity<*> {
        val tree = try {
            val jsonMapper = JsonMapper()
            jsonMapper.readTree(payload)
        } catch (e: IOException) {
            val mapper = JsonMapper()
            val json = mapper.writeValueAsString(XmlMapper().readTree(payload))
            mapper.readTree(json)
        }

        val requestHeaders = HttpHeaders()

        val responseBody = if (headers.accept.contains(MediaType.APPLICATION_JSON)) {
            requestHeaders.contentType = MediaType.APPLICATION_JSON
            JsonMapper().writeValueAsString(tree.fieldNames())
        } else {
            requestHeaders.contentType = MediaType.APPLICATION_XML
            val mapper = XmlMapper.builder().defaultUseWrapper(false).build()
            mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true)

            data class XmlFieldTag(
                @JacksonXmlElementWrapper(useWrapping = false)
                val tag: String
            )

            @JacksonXmlRootElement
            data class XmlFieldsDoc(
                @JacksonXmlElementWrapper(useWrapping = false)
                val tag: List<XmlFieldTag>
            )

            val objectSequence = tree.fieldNames().asSequence().map {
                XmlFieldTag(it)
            }.toList()

            val xmlDoc = XmlFieldsDoc(objectSequence)

            mapper.writeValueAsString(xmlDoc)
        }

        test.getTestServiceRandomStructureResponse(payload, tree)

        return ResponseEntity.ok()
            .headers(requestHeaders)
            .body(responseBody)
    }

    @PostMapping("/callback", consumes = ["application/json"])
    fun startCallbackSbpTransaction(@RequestBody callbackClientRequest: CallbackClientRequest): CallbackClientResponse? {
        return test.getTestServiceCallbackResponse(callbackClientRequest)
    }
}
