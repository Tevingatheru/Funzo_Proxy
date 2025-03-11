package com.funzo.funzoProxy.domain.question

interface QuestionService {
    fun addQuestion(
        examCode: String,
        questionText: String,
        image: String?,
    ): Question
    fun removeQuestion(questionCode: String)

    fun getQuestionsByExamCode(examCode: String): List<Question>

    fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionImage: String?
    ): Question

    fun getQuestionByCode(code: String): Question

    fun getAllQuestions(): List<Question>
}
