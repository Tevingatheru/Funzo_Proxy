package com.funzo.funzoProxy

import com.funzo.funzoProxy.domain.exam.ExamRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class FunzoProxyApplicationTests
{
	@Autowired
	private lateinit var examRepository: ExamRepository

	@Test
	fun contextLoads() {
	}
}
