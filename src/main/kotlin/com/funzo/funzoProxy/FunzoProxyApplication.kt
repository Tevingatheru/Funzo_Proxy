package com.funzo.funzoProxy

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
@EnableJpaRepositories
@SpringBootApplication
class FunzoProxyApplication

fun main(args: Array<String>) {
	runApplication<FunzoProxyApplication>(*args)
}
