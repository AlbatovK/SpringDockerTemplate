package com.albatros.springtemplate.presentation

import com.albatros.springtemplate.domain.model.iso.IsoMessage
import com.albatros.springtemplate.domain.model.iso.IsoResponseData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class WebSocketController {

    @Autowired
    private lateinit var template: SimpMessagingTemplate

    fun processIsoMessage(isoMessage: IsoMessage) = "We had no time to implement real ISO structure"
    @MessageMapping("/iso")
    fun processIso(@Payload isoMessage: IsoMessage) {
        processIsoMessage(isoMessage)
        template.convertAndSend(
            "/iso/${isoMessage.clientId}/data-stream",
            IsoResponseData(isoMessage.messageData)
        )
    }
}
