package com.funzo.funzoProxy.infrastructure.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration

class SwaggerConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI().apply {
            info(Info().title("Funzo API Doc").version("1.0"))
        }
    }
}