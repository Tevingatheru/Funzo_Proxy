package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
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
        private lateinit var subjectRepository: SubjectRepository
        @Mock
        private lateinit var generateCodeServiceImpl: GenerateCodeServiceImpl

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
        examService = ExamServiceImpl(examRepository, generateCodeServiceImpl = generateCodeServiceImpl, subjectRepository =  subjectRepository)
    }

    @Nested
    inner class WhenSaving{
        @Test
        fun shouldSaveSuccessfully() {
            examService.save(1, "S01")
            verify(examRepository).save(any())
        }
    }

    @Nested
    inner class WhenFindingByCode {

        @Test
        fun shouldFindByCode() {
            val expectedExam = Exam(level = 1,  subject = Subject() , code = "C01")
            `when`(examRepository.findByCode(examCode)).thenReturn(expectedExam)

            val result = examService.findByCode(examCode)

            assertEquals(expectedExam, result)
        }

        @Test
        fun shouldNotFindExamByCode() {
            `when`(examRepository.findByCode(examCode)).thenReturn(null)

            val examService = ExamServiceImpl(examRepository, generateCodeServiceImpl, subjectRepository)

            assertThatExceptionOfType(ChangeSetPersister.NotFoundException::class.java)
                .isThrownBy { examService.findByCode(examCode) }
        }
    }

    @Nested
    inner class WhenDeleting {
        @Test
        fun shouldDeleteExamByCode() {
            val examService = ExamServiceImpl(examRepository, generateCodeServiceImpl, subjectRepository)

            examService.deleteByCode(examCode)

            verify(examRepository).deleteByCode(examCode)
        }
    }
}