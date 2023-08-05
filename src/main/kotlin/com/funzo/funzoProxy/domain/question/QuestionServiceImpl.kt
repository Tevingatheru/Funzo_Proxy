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
        questionType: QuestionType,
        image: String?,
        correctOption: String,
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?
    ): AddQuestionResponse {
        val exam: Exam = getExamByCode(examCode)

        val questionTypeCode = generateCodeServiceImpl.generateCodeWithLength(7)
        val questionTypeObject = QuestionType.generateQuestionType(
            questionType,
            correctOption = correctOption,
            question = Question(exam = exam, question = questionText, type = null, image = null),
            optionA = optionA,
            optionB = optionB,
            optionC = optionC,
            optionD = optionD,
            code = questionTypeCode
        )
        val questionCode = generateCodeServiceImpl.generateCodeWithLength(7)
        val addedQuestion = Question(exam = exam,
            question = questionText,
            type =  questionType,
            image = image,
            code = questionCode,
            id = null)

        questionRepository.saveAndFlush(addedQuestion)

        return mapToAddQuestionsResponse(addedQuestion)
    }

    override fun removeQuestion(examCode: String, questionCode: String) {
        getExamByCode(examCode)
        questionRepository.delete(getQuestionByCode(questionCode))
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

        var question: Question = getQuestionByCode(code = questionCode)

        if (questionText != null) {
            question.question = questionText
        }

        if (questionImage != null) {
            question.image = questionImage
        }

        return mapToModifyQuestionResponse(question)
    }

    override fun getAllQuestions(): List<Question> {
        return questionRepository.findAll()
    }

    override fun getQuestionByCode(code: String): Question {
        return questionRepository.findByCode(code)
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
}
