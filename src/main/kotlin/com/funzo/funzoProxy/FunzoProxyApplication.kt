package com.funzo.funzoProxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FunzoProxyApplication

fun main(args: Array<String>) {
	try {
		runApplication<FunzoProxyApplication>(*args)
	} catch (e: Exception) {
		throw RuntimeException("Unable to start application.", e)
	}
}
