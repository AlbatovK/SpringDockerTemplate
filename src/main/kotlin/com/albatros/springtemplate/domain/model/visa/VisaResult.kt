package com.albatros.springtemplate.domain.model.visa

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class VisaResult(
    @JsonProperty("transaction_id")
    var transactionId: String,
    @JsonProperty("confirm_url")
    var confirmUrl: String,
) : Serializable
