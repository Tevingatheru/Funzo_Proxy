package com.funzo.funzoProxy

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition
class FunzoProxyApplication

fun main(args: Array<String>) {
	try {
		runApplication<FunzoProxyApplication>(*args)
	} catch (e: Exception) {
		throw RuntimeException("Unable to start application.", e)
	}
}
