package com.albatros.springtemplate.domain.model.webhook

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CallbackClientRequest(
    var id: String,
    @JsonProperty("post_url")
    var postUrl: String,
    var amount: CallbackClientRequestAmount
) : Serializable