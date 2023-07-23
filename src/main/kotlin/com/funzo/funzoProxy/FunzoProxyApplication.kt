package com.funzo.funzoProxy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class FunzoProxyApplication

fun main(args: Array<String>) {
	runApplication<FunzoProxyApplication>(*args)
}
