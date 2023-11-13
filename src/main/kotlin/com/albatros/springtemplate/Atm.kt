package com.albatros.springtemplate

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "atm")
class Atm {
    @Id
    var address: String? = "a"
    var latitude = 0.0
    var longitude = 0.0
    var allDay = false
    var wheelchairCapability = false
    var wheelchairActivity = false
    var blindCapability = false
    var blindActivity = false
    var nfcForBankCardsCapability = false
    var nfcForBankCardActivity = false
    var qrReadCapability = false
    var qrReadActivity = false
    var supportUsdCapability = false
    var supportUsdActivity = false
    var supportsChargeRubCapability = false
    var supportChargeRubActivity = false
    var supportsEurCapability = false
    var supportEurActivity = false
    var supportsRubCapability = false
    var supportRubActivity = false
}
