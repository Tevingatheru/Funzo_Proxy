package com.funzo.funzoProxy.domain.question

interface QuestionService {
    fun addQuestion(
        examCode: String,
        questionText: String,
        image: String?,
    ): AddQuestionResponse
    fun removeQuestion(questionCode: String)

    fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse

    fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionImage: String?
    ): Question

    fun getQuestionByCode(code: String): Question

    fun getAllQuestions(): List<Question>
}
