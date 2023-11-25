package com.albatros.springtemplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class SpringtemplateApplication

fun main(args: Array<String>) {
    runApplication<SpringtemplateApplication>(*args)
}
