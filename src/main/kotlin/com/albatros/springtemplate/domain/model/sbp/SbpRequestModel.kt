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
 *   <request>
 * 	<pg_card_number>5266886676134311</pg_card_number>
 * 	<pg_description>Оплата брони</pg_description>
 * 	<pg_cardholder>Ivan Ivanov</pg_cardholder>
 *     <pg_expire_date>0128</pg_expire_date>
 *     <pg_cvv>555</pg_cvv>
 *   </request>
 */

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
@JacksonXmlRootElement(localName = "request")
data class SbpRequestModel(
    @XmlElement(name = "pg_card_number")
    @JacksonXmlProperty(localName = "pg_card_number")
    var pgCardNumber: String = "",
    @JacksonXmlProperty(localName = "pg_description")
    @XmlElement(name = "pg_description")
    var pgDescription: String = "",
    @JacksonXmlProperty(localName = "pg_cardholder")
    @XmlElement(name = "pg_cardholder")
    var pgCardHolder: String = "",
    @JacksonXmlProperty(localName = "pg_expire_date")
    @XmlElement(name = "pg_expire_date")
    var pgExpireDate: String = "",
    @JacksonXmlProperty(localName = "pg_cvv")
    @XmlElement(name = "pg_cvv")
    var pgCVV: String = ""
) : Serializable
