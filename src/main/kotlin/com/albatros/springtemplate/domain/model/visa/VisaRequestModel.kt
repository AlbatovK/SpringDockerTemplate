package com.albatros.springtemplate.domain.model.visa

import java.io.Serializable

/**
 * {
 *  "method": "payment",
 *  "params": {
 *     "card_holder_name": "CARDHOLDER NAME",
 *     "card_number": "4278011111275400",
 *     "card_expire": "2702",
 *     "card_cvc": "067",
 *     "amount": "1000",
 *     "description": "Month subscription",
 *     "redirect_url": "https://shop.merchant.com/order/23"
 *   },
 *   "id": "4490d1a0-fd21-44b6-84cc-a72d17b1c962"
 * }
 */

data class VisaRequestModel(
    var method: String,
    var params: VisaParams,
    var id: String
) : Serializable
