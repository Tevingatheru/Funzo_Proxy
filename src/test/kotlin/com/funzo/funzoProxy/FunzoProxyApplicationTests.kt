package com.funzo.funzoProxy

import org.junit.jupiter.api.Test
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(
	classes = [TestContainerConfig::class])
open class FunzoProxyApplicationTests
{
	@Test
	fun contextLoads() {
	}
}
