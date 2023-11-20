package com.albatros.springtemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@EnableCaching
class SpringtemplateApplication


@RestController
class Controller {

}

fun main(args: Array<String>) {
    runApplication<SpringtemplateApplication>(*args)
}
