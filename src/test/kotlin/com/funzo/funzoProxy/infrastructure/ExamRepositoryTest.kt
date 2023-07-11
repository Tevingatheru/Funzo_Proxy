package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.TestContainerConfig
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringJUnitConfig(TestContainerConfig::class)
@ExtendWith(SpringExtension::class)
class ExamRepositoryTest: TestContainerConfig() {

    @Autowired
    private lateinit var examRepository: ExamRepository

    @Test
    fun testSaveExam() {
        val exam = Exam(1, "E001")

        examRepository.save(exam)

        val retrievedExam = examRepository.findByCode("E001")

        assertNotNull(retrievedExam)
    }
}
