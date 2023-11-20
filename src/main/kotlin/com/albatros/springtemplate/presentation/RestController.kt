package com.albatros.springtemplate.presentation

import com.albatros.springtemplate.domain.model.User
import com.albatros.springtemplate.domain.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController
import java.awt.PageAttributes.MediaType

@RestController(value = "/user")
class RestController {

    @Autowired
    lateinit var service: UserService

    @GetMapping("/get/all")
    fun getAll() = service.getAll()

    @GetMapping("/delete/{userId}")
    fun deleteUser(@PathVariable userId: Long) = service.deleteById(userId)

    @PostMapping("/save", consumes = ["application/json"])
    fun saveUser(@RequestBody user: User) = service.saveUser(user)

    @GetMapping("/get/{userId}")
    fun getById(@PathVariable userId: Long) = service.getUserById(userId)

}