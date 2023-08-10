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
    ): AddQuestionResponse {
        val addedQuestion = Question(exam = getExamByCode(examCode),
            question = questionText,
            type =  null,
            image = image,
            code = generateCodeServiceImpl.generateCodeWithLength(7),
            id = null)

        questionRepository.saveAndFlush(addedQuestion)

        return mapToAddQuestionsResponse(addedQuestion)
    }

    override fun removeQuestion(questionCode: String) {

        questionRepository.delete(getQuestionByCode(questionCode))
    }

    override fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse {
        val exam = getExamByCode(examCode = examCode)

        return mapToExamQuestionsResponse(exam)
    }

    private fun mapToExamQuestionsResponse(exam: Exam) = ExamQuestionsResponse(exam.questions)

    override fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionImage: String?
    ): Question {
        getExamByCode(examCode = examCode)

        var question: Question = getQuestionByCode(code = questionCode)

        if (questionText != null) {
            question.question = questionText
        }

        if (questionImage != null) {
            question.image = questionImage
        }

        return question
    }

    override fun getAllQuestions(): List<Question> {
        return questionRepository.findAll()
    }

    override fun getQuestionByCode(code: String): Question {
        return questionRepository.findByCode(code)
    }

    private fun mapToAddQuestionsResponse(question: Question): AddQuestionResponse {
        return AddQuestionResponse(code = question.code)
    }

    private fun getExamByCode(examCode: String): Exam {
        return examRepository.findByCode(examCode) ?: throw NotFoundException()
    }
}
