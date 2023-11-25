package com.albatros.springtemplate.domain.model.webhook

import java.io.Serializable

data class CallbackServerRequestBody(
    var id: String,
    var status: String
): Serializable