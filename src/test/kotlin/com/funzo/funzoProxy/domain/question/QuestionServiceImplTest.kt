package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.openMocks
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

class QuestionServiceImplTest {
    @Mock
    private lateinit var questionRepository: QuestionRepository
    @Mock
    private lateinit var examRepository: ExamRepository

    @InjectMocks
    private lateinit var questionServiceImpl: QuestionServiceImpl

    @BeforeEach
    fun setUp() {
        openMocks(this)
        questionServiceImpl = QuestionServiceImpl(questionRepository = questionRepository, examRepository = examRepository)
    }

    @Nested
    inner class WhenAddingQuestion {
        @Test
        fun throwsExceptionWhenGetExamFails() {
            assertThatExceptionOfType(NotFoundException::class.java).isThrownBy {
                questionServiceImpl.addQuestion("E01", "Question", TrueOrFalseQuestion(), null,)
            }
        }

        @Test
        fun questionIsSaved() {
            `when`(questionRepository.saveAndFlush(any())).thenReturn()
            assertThatExceptionOfType(NotFoundException::class.java).isThrownBy {
                questionServiceImpl.addQuestion("E01", "Question", TrueOrFalseQuestion(), null,)
            }
        }
    }

    @Nested
    inner class WhenDeletingQuestion {

        @Test
        fun removeQuestion() {
        }
    }

    @Nested
    inner class FetchingQuestionsByExam {
        @Test
        fun getQuestionsByExamCode() {
        }
    }

    @Nested
    inner class WhenModifyingQuestionDetails {

        @Test
        fun modifyQuestion() {
        }
    }

    @Nested
    inner class FetchingAllQuestions {

        @Test
        fun getAllQuestions() {
        }
    }

    @Nested
    inner class FetchingQuestionByCode {

        @Test
        fun getQuestionByCode() {
        }
    }
}
