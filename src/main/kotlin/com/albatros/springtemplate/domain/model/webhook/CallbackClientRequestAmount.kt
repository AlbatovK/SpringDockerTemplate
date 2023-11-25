package com.albatros.springtemplate.domain.model.webhook

import java.io.Serializable

data class CallbackClientRequestAmount(
    var value: Int,
    var currency: String
) : Serializable