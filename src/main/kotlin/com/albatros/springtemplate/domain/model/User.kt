package com.albatros.springtemplate.domain.model

import java.io.Serializable
import javax.persistence.*

@Entity(name = "users")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = -1,
    var name: String = ""
) : Serializable