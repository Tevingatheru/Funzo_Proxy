package com.funzo.funzoProxy

import org.junit.jupiter.api.Test
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(
	classes = [TestContainerConfig::class])
class FunzoProxyApplicationTests
{
	@Test
	fun contextLoads() {
	}
}
