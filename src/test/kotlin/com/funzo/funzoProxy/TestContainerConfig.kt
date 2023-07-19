package com.funzo.funzoProxy

import com.funzo.funzoProxy.domain.exam.ExamRepository
import com.funzo.funzoProxy.domain.user.UserRepository
import com.funzo.funzoProxy.infrastructure.ExamRepositoryImpl
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

@Configuration
class TestContainerConfig {
    @Bean
    fun examRepository(): ExamRepository {
        return ExamRepositoryImpl()
    }

    @MockBean
    private lateinit var userRepository: UserRepository

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("root")
            withPassword("mysecretpassword")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)

        }
    }
}
