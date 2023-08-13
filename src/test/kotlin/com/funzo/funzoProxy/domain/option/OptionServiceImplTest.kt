package com.funzo.funzoProxy.domain.option

import com.funzo.funzoProxy.TestDataUtil
import com.funzo.funzoProxy.domain.option.factory.OptionFactory
import com.funzo.funzoProxy.domain.option.strategy.UpdateStrategy
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.infrastructure.GenerateCodeService
import com.funzo.funzoProxy.infrastructure.jpa.OptionRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import java.lang.IllegalArgumentException
import java.util.stream.Stream

class OptionServiceImplTest {
    companion object {
        private const val option = "option"
        private const val question = "question"
        private const val code = "code"

        @JvmStatic
        fun shouldThrowExceptionCausedByNullOptionProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(option),
                Arguments.of(question),
                Arguments.of(code),
                Arguments.of(null)
            )
        }
    }

    @Mock
    private lateinit var updateStrategy: UpdateStrategy
    @Mock
    private lateinit var optionFactory: OptionFactory
    @Mock
    private lateinit var  generateCodeService: GenerateCodeService
    @Mock
    private lateinit var  questionRepository: QuestionRepository
    @Mock
    private lateinit var  optionRepository: OptionRepository

    @InjectMocks
    private lateinit var optionServiceImpl: OptionServiceImpl

    @BeforeEach
    fun setUp() {
        openMocks(this)
        optionServiceImpl = OptionServiceImpl(optionRepository = optionRepository,
            questionRepository = questionRepository,
            generateCodeService = generateCodeService,
            optionFactory = optionFactory,
            updateStrategy = updateStrategy)
    }

    @Nested
    inner class CreatingOption {
        @Test
        fun shouldGenerateCodeWithLengthOfSevenSuccessfully() {
            `when`(optionRepository.saveAndFlush(any())).thenReturn(TestDataUtil.OptionData.randomOption())
            causeNullOptionSetUp()

            `when`(optionFactory.create(any()))
                .thenReturn(TestDataUtil.OptionData.randomOption())

            optionServiceImpl.createOption(optionA = TestDataUtil.OptionData.randomOptionText(),
                optionB = TestDataUtil.OptionData.randomOptionText(),
                optionC = TestDataUtil.OptionData.randomOptionText(),
                optionD = TestDataUtil.OptionData.randomOptionText(),
                correctOption = TestDataUtil.OptionData.randomBooleanOptionText(),
                questionCode = TestDataUtil.OptionData.randomOptionText())

            verify(generateCodeService).generateCodeWithLength(7)
        }

        @ParameterizedTest
        @MethodSource(value = ["com.funzo.funzoProxy.domain.option.OptionServiceImplTest#shouldThrowExceptionCausedByNullOptionProvider"])
        fun shouldThrowExceptionCausedByNullOption(parameters: String?) {
            when(parameters) {
                option -> causeNullOptionSetUp()
                question -> causeNullQuestion()
                code -> {}
                else -> {
                    causeNullOptionSetUp()
                    `when`(optionFactory.create(any()))
                        .thenThrow(NullPointerException())
                }
            }
            assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
                optionServiceImpl.createOption(optionA = TestDataUtil.OptionData.randomOptionText(),
                    optionB = TestDataUtil.OptionData.randomOptionText(),
                    optionC = TestDataUtil.OptionData.randomOptionText(),
                    optionD = TestDataUtil.OptionData.randomOptionText(),
                    correctOption = TestDataUtil.OptionData.randomBooleanOptionText(),
                    questionCode = TestDataUtil.OptionData.randomOptionText()
                )}.withCauseInstanceOf(NullPointerException::class.java)
                .havingCause()
        }

        private fun causeNullOptionSetUp() {
            causeNullQuestion()
            `when`(questionRepository.findByCode(anyString())).thenReturn(/* value = */ Question(
                exam = TestDataUtil.OptionData.randomExam(),
                question = TestDataUtil.OptionData.randomOptionText(),
                option = TestDataUtil.OptionData.randomOption(),
                code = TestDataUtil.OptionData.randomCode()
            )
            )
        }
        private fun causeNullQuestion() {
            `when`(generateCodeService.generateCodeWithLength(anyInt()))
                .thenReturn(TestDataUtil.OptionData.randomCode())
        }
    }

    @Nested
    inner class DeletingOption {
        private val expectedCode = TestDataUtil.OptionData.randomCode()

        @Test
        fun deleteByCodeSuccessfully() {
            optionServiceImpl.deleteByCode(expectedCode)
            verify(optionRepository).deleteByCode(expectedCode)
        }

        @Test
        fun failToDeleteByCode() {
            `when`(optionRepository.deleteByCode(anyString())).thenThrow(IllegalArgumentException())
            assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
                optionServiceImpl.deleteByCode(expectedCode)
            }
            verify(optionRepository).deleteByCode(expectedCode)
        }
    }

    @Nested
    inner class FetchingByCode {
        @Test
        fun canGetByCodeSuccessfully() {
            val expectedCode = TestDataUtil.OptionData.randomCode()
            optionServiceImpl.getByCode(expectedCode)
            verify(optionRepository).getByCode(expectedCode)
        }

        @Test
        fun failToGetByCode() {
            `when`(optionRepository.getByCode(anyString()))
                .thenThrow(IllegalArgumentException())
            val expectedCode = TestDataUtil.OptionData.randomCode()
            assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
                optionServiceImpl.getByCode(expectedCode)
            }.withMessageContaining("Unable to get option by code: code")
            verify(optionRepository).getByCode(expectedCode)
        }
    }

    @Nested
    inner class FetchingByQuestionCode {
        private val code = TestDataUtil.OptionData.randomCode()

        @Test
        fun getByQuestionCode() {
            `when`(optionRepository.getByQuestionCode(anyString()))
                .thenReturn(TestDataUtil.OptionData.randomOption())
            val option = optionServiceImpl.getByQuestionCode(
                questionCode = code
            )
            verify(optionRepository).getByQuestionCode(code)
        }

        @Test
        fun failGetByQuestionCode() {
            `when`(optionRepository.getByQuestionCode(anyString()))
                .thenThrow(IllegalArgumentException())

            assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
                optionServiceImpl.getByQuestionCode(
                    questionCode = code
                )
            }
            verify(optionRepository).getByQuestionCode(code)
        }
    }

    @Nested
    inner class ModifyingOptionDetails {
        @Test
        fun modifyOptionSuccessfully() {
            `when`(optionRepository.getByCode(anyString()))
                .thenReturn(TestDataUtil.OptionData.randomOption())
            `when`(optionRepository.saveAndFlush(any())).thenReturn(TestDataUtil.OptionData.randomOption())

            optionServiceImpl.modifyOption(code = TestDataUtil.OptionData.randomCode(),
                optionA = TestDataUtil.OptionData.randomOptionText(),
                optionB = TestDataUtil.OptionData.randomOptionText(),
                optionC = TestDataUtil.OptionData.randomOptionText(),
                optionD = TestDataUtil.OptionData.randomOptionText(),
                correctOption = TestDataUtil.OptionData.randomOptionText())
        }

        @Test
        fun throwExceptionCausedOptionNotFound() {
            `when`(optionRepository.getByCode(anyString()))
                .thenThrow(IllegalArgumentException())

            assertThatExceptionOfType(RuntimeException::class.java)
                .isThrownBy {
                    optionServiceImpl.modifyOption(code = TestDataUtil.OptionData.randomCode(),
                        optionA = TestDataUtil.OptionData.randomOptionText(),
                        optionB = TestDataUtil.OptionData.randomOptionText(),
                        optionC = TestDataUtil.OptionData.randomOptionText(),
                        optionD = TestDataUtil.OptionData.randomOptionText(),
                        correctOption = TestDataUtil.OptionData.randomOptionText())
                }.withMessageContaining("Unable to update option. code")
        }
    }

    @Nested
    inner class FetchingAll {
        @Test
        fun shouldFindAllSuccessfully() {
            optionServiceImpl.findAll()
        }

        @Test
        fun shouldThrowExceptionWithMessage() {
            `when`(optionRepository.findAll())
                .thenThrow(IllegalArgumentException::class.java)

            assertThatExceptionOfType(RuntimeException::class.java).isThrownBy {
                optionServiceImpl.findAll()
            }.withMessageContaining("Unable to find all options")
        }
    }
}
