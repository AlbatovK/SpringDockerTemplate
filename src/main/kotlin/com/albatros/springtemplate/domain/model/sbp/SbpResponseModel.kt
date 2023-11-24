package com.albatros.springtemplate.domain.model.sbp

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.io.Serializable
import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

/**
 * <?xml version="1.0" encoding="utf-8"?>
 *   <response>
 *  <pg_status>rejected</pg_status>
 *  <pg_description>Бронь истекла</pg_description>
 *  <pg_id>123</pg_id>
 *   </response>
 */

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "response")
data class SbpResponseModel(
    @JacksonXmlProperty(localName = "pg_status")
    @XmlElement(name = "pg_status")
    var pgStatus: String = "",
    @JacksonXmlProperty(localName = "pg_description")
    @XmlElement(name = "pg_description")
    var pgDescription: String = "",
    @JacksonXmlProperty(localName = "pg_id")
    @XmlElement(name = "pg_id")
    var pgId: String = ""
) : Serializable
