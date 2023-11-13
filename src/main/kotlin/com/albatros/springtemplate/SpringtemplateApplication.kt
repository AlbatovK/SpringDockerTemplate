package com.albatros.springtemplate

import io.swagger.v3.core.model.ApiDescription
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringtemplateApplication

@RestController
class Controller {
    @GetMapping("/hi")
    fun getHi() = atmRepository.findAll()

    @GetMapping("/a")
    fun getAi() = atmRepository.save(Atm())

    @Autowired
    lateinit var atmRepository: AtmRepository
}

fun apied() {

}

fun main(args: Array<String>) {
    runApplication<SpringtemplateApplication>(*args)
}
