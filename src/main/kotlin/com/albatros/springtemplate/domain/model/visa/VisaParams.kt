package com.albatros.springtemplate.domain.model.visa

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class VisaParams(
    @JsonProperty("card_holder_name")
    var cardHolderName: String,
    @JsonProperty("card_number")
    var cardNumber: String,
    @JsonProperty("card_expire")
    var cardExpire: String,
    @JsonProperty("card_cvc")
    var cardCVC: String,
    var amount: String,
    var description: String,
    @JsonProperty("redirect_url")
    var redirectUrl: String
) : Serializable
