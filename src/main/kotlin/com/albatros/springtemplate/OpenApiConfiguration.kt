package com.albatros.springtemplate

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .info(
            Info().apply {
                title = "SpringDockerTemplate"
                description = "PostgreSQL + Docker + Github workflow CI/CD Spring boot backend template"
                version = "v0.0.1"
                license = License().name("Apache 2.0")

            }
        )
        .externalDocs(
            ExternalDocumentation().apply {
                description = "SpringTemplate Github Docs"
                url = "https://github.com/AlbatovK/SpringDockerTemplate"
            }
        )
}