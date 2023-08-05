package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.TestDataUtil
import com.funzo.funzoProxy.domain.result.ResultServiceImpl.Companion.UNABLE_TO_FIND_RESULTS
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.ResultRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import java.lang.IllegalArgumentException
import java.lang.RuntimeException
import java.util.*

class ResultServiceImplTest {

    @Mock
    private lateinit var generateCodeServiceImpl: GenerateCodeServiceImpl
    @Mock
    private lateinit var userRepository: UserRepository
    @Mock
    private lateinit var examRepository: ExamRepository
    @Mock
    private lateinit var resultRepository: ResultRepository

    @InjectMocks
    private lateinit var resultServiceImpl: ResultServiceImpl

    @BeforeEach
    fun setUp() {
        openMocks(this)
        resultServiceImpl = ResultServiceImpl(resultRepository = resultRepository,
            examRepository = examRepository,
            userRepository = userRepository,
            generateCodeServiceImpl = generateCodeServiceImpl)
    }

    @Nested
    inner class AddingResults {
        @Test
        fun shouldThrowWithMessageWhenSaveFlushFails() {
            `when`(examRepository.findByCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.exam)
            `when`(userRepository.findStudentByUserCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.student)
            `when`(generateCodeServiceImpl.generateCodeWithLength(anyInt()))
                .thenReturn(TestDataUtil.ResultData.resultCode)
            `when`(resultRepository.saveAndFlush(any())).thenThrow(IllegalArgumentException())

            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.createResult(examCode = TestDataUtil.ResultData.examCode,
                        userCode =  TestDataUtil.ResultData.userCode,
                        score = TestDataUtil.ResultData.score)
                }

            verify(examRepository, times(1)).findByCode(anyString())
            verify(userRepository, times(1)).findStudentByUserCode(anyString())
            verify(resultRepository, times(1)).findByExamCodeAndUserCode(anyString(), anyString())
            verify(generateCodeServiceImpl, times(1)).generateCodeWithLength(anyInt())
            verify(resultRepository, times(1)).saveAndFlush(any())
        }

        @Test
        fun shouldThrowWithMessageWhenCodeGenerationFails() {
            `when`(examRepository.findByCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.exam)
            `when`(userRepository.findStudentByUserCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.student)
            `when`(generateCodeServiceImpl.generateCodeWithLength(anyInt())).thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.createResult(examCode = TestDataUtil.ResultData.examCode,
                        userCode = TestDataUtil.ResultData.userCode,
                        score = TestDataUtil.ResultData.score)
                }
        }

        @Test
        fun shouldThrowWithMessageWhenFindByExamCodeAndUserCodeFails() {
            `when`(examRepository.findByCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.exam)
            `when`(userRepository.findStudentByUserCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.student)
            `when`(resultRepository.findByExamCodeAndUserCode(anyString(), anyString()))
                .thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.createResult(examCode = TestDataUtil.ResultData.examCode,
                        userCode = TestDataUtil.ResultData.userCode, score = TestDataUtil.ResultData.score)
                }
        }

        @Test
        fun shouldFailToFindStudentThrowing() {
            `when`(examRepository.findByCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.exam)
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.createResult(examCode = TestDataUtil.ResultData.examCode,
                        TestDataUtil.ResultData.userCode, score = TestDataUtil.ResultData.score)
                }.withMessageContaining("NotFoundException")

            verify(examRepository, times(1)).findByCode(anyString())
            verify(userRepository, times(1)).findStudentByUserCode(anyString())
            verify(resultRepository, times(0)).findByExamCodeAndUserCode(anyString(), anyString())
            verify(generateCodeServiceImpl, times(0)).generateCodeWithLength(anyInt())
            verify(resultRepository, times(0)).saveAndFlush(any())
        }

        @Test
        fun shouldFailToFindExamThrowingNullPointer() {
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.createResult(examCode = TestDataUtil.ResultData.examCode,
                        userCode = TestDataUtil.ResultData.userCode, score = TestDataUtil.ResultData.score)
                }.withMessageContaining("NotFoundException").withMessageNotContaining("parameter")

            verify(examRepository, times(1)).findByCode(anyString())
            verify(userRepository, times(0)).findStudentByUserCode(anyString())
            verify(resultRepository, times(0)).findByExamCodeAndUserCode(anyString(), anyString())
            verify(generateCodeServiceImpl, times(0)).generateCodeWithLength(anyInt())
            verify(resultRepository, times(0)).saveAndFlush(any())
        }
    }

    @Nested
    inner class FetchingByCode {
        @Test
        fun shouldFailToFindResultByCodeWithMessage() {
            `when`(resultRepository.findByCode(anyString()))
                .thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.findByCode(TestDataUtil.ResultData.resultCode)
                }
            verify(resultRepository).findByCode(anyString())
        }

        @Test
        fun shouldFindResultByCode() {
            `when`(resultRepository.findByCode(anyString()))
                .thenReturn(TestDataUtil.ResultData.result)
            resultServiceImpl.findByCode(TestDataUtil.ResultData.resultCode)
            verify(resultRepository).findByCode(anyString())
        }
    }

    @Nested
    inner class FetchingAllResults {
        @Test
        fun failWithMessage() {
            `when`(resultRepository.findAll())
                .thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy { resultServiceImpl.findAll()  }
                .withMessageContaining(UNABLE_TO_FIND_RESULTS)
        }

        @Test
        fun resultShouldNotBeEmpty() {
            `when`(resultRepository.findAll())
                .thenReturn(Collections.singletonList(TestDataUtil.ResultData.result))
            val allResults = resultServiceImpl.findAll()
            assertThat(allResults.isNotEmpty()).isTrue()
        }
    }

    @Nested
    inner class FetchingByUserCode {

        @Test
        fun findResultsByUserCodeFailsWithMessage() {
            `when`(resultRepository.findByUserCode(anyString()))
                .thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy { resultServiceImpl.findResultsByUserCode(userCode = TestDataUtil.ResultData.userCode) }
        }

        @Test
        fun shouldFindResultsByUserCodeSuccessfully() {
            `when`(resultRepository.findByUserCode(anyString()))
                .thenReturn(Collections.singletonList(TestDataUtil.ResultData.result))
            val resultList = resultServiceImpl.findResultsByUserCode(userCode = TestDataUtil.ResultData.userCode)
            assertThat(resultList.isNotEmpty()).isTrue()
        }
    }

    @Nested
    inner class FetchingResultByExamCodeAndUserCode {

        @Test
        fun shouldFailFindResultsByExamCodeAndUserCode() {
            `when`(resultRepository.findByExamCodeAndUserCode(anyString(), anyString()))
                .thenThrow(IllegalArgumentException())

            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    resultServiceImpl.findResultsByExamCodeAndUserCode(
                        examCode = TestDataUtil.ResultData.examCode, userCode = TestDataUtil.ResultData.userCode)
                }.withMessageContaining(TestDataUtil.ResultData.examCode, TestDataUtil.ResultData.userCode)

            verify(resultRepository).findByExamCodeAndUserCode(anyString(), anyString())
        }

        @Test
        fun shouldFindResultsByExamCodeAndUserCodeSuccessfully() {
            resultServiceImpl.findResultsByExamCodeAndUserCode("E01", "U01")
            verify(resultRepository).findByExamCodeAndUserCode(anyString(), anyString())
        }
    }

    @Nested
    inner class WhenDeletingByCode() {

        @Test
        fun throwIfDeleteByCodeFailed() {
            `when`(resultRepository.deleteByCode(anyString())).thenThrow(IllegalArgumentException())

            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy { resultServiceImpl.deleteByCode(TestDataUtil.ResultData.resultCode) }
                .withMessageContaining(TestDataUtil.ResultData.resultCode)
            verify(resultRepository).deleteByCode(anyString())
        }

        @Test
        fun shouldVerifyDeleteByCode() {
            resultServiceImpl.deleteByCode(TestDataUtil.ResultData.resultCode)
            verify(resultRepository).deleteByCode(anyString())
        }
    }
}
