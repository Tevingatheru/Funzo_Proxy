package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.QuestionRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

class QuestionServiceImpl(
    private val questionRepository: QuestionRepository,
    private val examRepository: ExamRepository
) : QuestionService {

    override fun addQuestion(
        examCode: String,
        question: String,
        questionType: QuestionType,
        image: String
    ): AddQuestionResponse {
        val exam: Exam = getExamByCode(examCode)

        val addedQuestion = Question(exam, question, questionType, image)

        var savedQuestion = questionRepository.save(addedQuestion)

        savedQuestion = QuestionType.generateQuestionType(questionType, savedQuestion)

        questionRepository.save(savedQuestion)

        return mapToAddQuestionsResponse(savedQuestion)
    }

    override fun removeQuestion(examCode: String, questionCode: String) {
        val exam = getExamByCode(examCode)
        if (exam != null) {
            questionRepository.delete(getQuestionByCode(questionCode))
        }
    }

    override fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse {
        val exam = getExamByCode(examCode = examCode)
        return ExamQuestionsResponse(exam.questions)
    }

    override fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionType: QuestionType?,
        questionImage: String?
    ): EditQuestionResponse {
        getExamByCode(examCode = examCode)

        var question: Question = getQuestionByCode(questionCode = questionCode)

        if (questionText != null) {
            question.question = questionText
        }
        if (questionType != null) {
            question = QuestionType.generateQuestionType(questionType, question)
        }
        if (questionImage != null) {
            question.image = questionImage
        }

        return mapToModifyQuestionResponse(question)
    }

    private fun mapToModifyQuestionResponse(question: Question): EditQuestionResponse {
        return EditQuestionResponse(question.code, question.image, question.question)
    }

    private fun mapToAddQuestionsResponse(question: Question): AddQuestionResponse {
        return AddQuestionResponse(code = question.code)
    }

    private fun getExamByCode(examCode: String): Exam {
        return examRepository.findByCode(examCode) ?: throw NotFoundException()
    }

    private fun getQuestionByCode(questionCode: String): Question {
        return questionRepository.findByCode(questionCode)
    }

}
