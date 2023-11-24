package com.albatros.springtemplate.domain.model.visa

import java.io.Serializable

/**
 * {
 *     "id": "10e65eb0-913f-4d24-b377-ae437766f24c",
 *     "result": {
 *         "transaction_id": "1485295",
 *         "confirm_url": "https://ecomm.kapital.ru:6443/ecomm2/ClientHandler?trans_id=tYkiAVsnP3SnjBMSiXrWGrIRbeo%3D"
 *     }
 * }
 */

data class VisaResponseModel(
    var id: String,
    var result: VisaResult
) : Serializable
