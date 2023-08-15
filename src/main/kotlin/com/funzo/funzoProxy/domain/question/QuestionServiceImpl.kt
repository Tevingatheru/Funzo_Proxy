package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class QuestionServiceImpl(
    private val questionRepository: QuestionRepository,
    private val examRepository: ExamRepository,
    private val generateCodeServiceImpl: GenerateCodeServiceImpl
) : QuestionService {

    override fun addQuestion(
        examCode: String,
        questionText: String,
        image: String?,
    ): Question {
        try {
            val addedQuestion = Question(exam = getExamByCode(examCode),
                question = questionText,
                option =  null,
                image = image,
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                id = null)

            return questionRepository.saveAndFlush(addedQuestion)
        } catch (e: Exception) {
            throw RuntimeException("Unable to add questions. examCode: $examCode")
        }
    }

    override fun removeQuestion(questionCode: String) {
        try {
            questionRepository.delete(getQuestionByCode(questionCode))
        } catch (e: Exception) {
            throw RuntimeException("Unable to delete questions. code: $questionCode")
        }
    }

    override fun getQuestionsByExamCode(examCode: String): List<Question> {
        try {
            val exam = getExamByCode(examCode = examCode)

            return exam.questions!!
        } catch (e: Exception) {
            throw RuntimeException("Unable to get questions by examCode. examCode : $examCode")
        }
    }

    override fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionImage: String?
    ): Question {
        try {
            getExamByCode(examCode = examCode)

            var question: Question = getQuestionByCode(code = questionCode)

            if (questionText != null && !questionText.equals(question.question)) {
                question.question = questionText
            }

            if (questionImage != null && !questionImage.equals(questionImage)) {
                question.image = questionImage
            }

            return questionRepository.saveAndFlush(question)
        } catch (e: Exception) {
            throw RuntimeException("Unable to modify question. ExamCode: $examCode, code $questionCode")
        }
    }

    override fun getAllQuestions(): List<Question> {
        return try {
            questionRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException("Unable to get all questions")
        }
    }

    override fun getQuestionByCode(code: String): Question {
        return try {
            questionRepository.findByCode(code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to get question by code: $code")
        }
    }

    private fun getExamByCode(examCode: String): Exam {
        return try {
            examRepository.findByCode(examCode) ?: throw NotFoundException()
        } catch (e: Exception) {
            throw RuntimeException("Unable to find exam with code: $examCode")
        }
    }
}
