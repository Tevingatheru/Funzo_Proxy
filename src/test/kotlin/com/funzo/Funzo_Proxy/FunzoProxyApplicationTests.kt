package com.funzo.Funzo_Proxy

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.junit.jupiter.Testcontainers

@ExtendWith(SpringExtension::class)
@SpringBootTest
@Testcontainers
class FunzoProxyApplicationTests {

	@Test
	fun contextLoads() {
	}

}
