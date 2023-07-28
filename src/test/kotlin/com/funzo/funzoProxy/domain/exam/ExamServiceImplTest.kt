package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.data.crossstore.ChangeSetPersister

class ExamServiceImplTest {
    companion object {
        @Mock
        @JvmStatic
        private lateinit var examRepository: ExamRepository

        @InjectMocks
        private lateinit var examService: ExamServiceImpl

        const val examCode = "EXAM002"
    }

    @BeforeEach
    fun setUp() {
        openMocks(this)
        examService = ExamServiceImpl(examRepository)
    }

    @Nested
    inner class WhenSaving{
        @Test
        fun shouldSaveSuccessfully() {
            examService.save(CreateExamCommand(1, "S01"))
            verify(examRepository).save(any())
        }
    }

    @Nested
    inner class WhenFindingByCode {

        @Test
        fun shouldFindByCode() {
            val expectedExam = Exam(1)
            `when`(examRepository.findByCode(examCode)).thenReturn(expectedExam)

            val result = examService.findByCode(examCode)

            assertEquals(expectedExam, result)
        }

        @Test
        fun shouldNotFindExamByCode() {
            `when`(examRepository.findByCode(examCode)).thenReturn(null)

            val examService = ExamServiceImpl(examRepository)

            assertThatExceptionOfType(ChangeSetPersister.NotFoundException::class.java)
                .isThrownBy { examService.findByCode(examCode) }
        }
    }

    @Nested
    inner class WhenDeleting {
        @Test
        fun shouldDeleteExamByCode() {
            val examService = ExamServiceImpl(examRepository)

            examService.deleteByCode(examCode)

            verify(examRepository).deleteByCode(examCode)
        }
    }
}