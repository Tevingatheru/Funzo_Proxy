package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.TestContainerConfig
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig

@SpringJUnitConfig(TestContainerConfig::class)
@DataJpaTest
class ExamRepositoryTest
{

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
