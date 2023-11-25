package com.albatros.springtemplate.domain.model.webhook

import java.io.Serializable

data class CallbackClientResponse(
    var result: String,
    var link: String
) : Serializable