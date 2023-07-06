package com.funzo.Funzo_Proxy

import org.springframework.boot.fromApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.with

@TestConfiguration(proxyBeanMethods = false)
class TestFunzoProxyApplication

fun main(args: Array<String>) {
	fromApplication<FunzoProxyApplication>().with(TestFunzoProxyApplication::class).run(*args)
}
