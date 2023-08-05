package com.funzo.funzoProxy.domain.question

interface QuestionService {
    fun addQuestion(
        examCode: String,
        question: String,
        questionType: QuestionType,
        image: String?,
        correctOption: String,
        optionA: String?,
        optionB: String?,
        optionC: String?,
        optionD: String?
    ): AddQuestionResponse
    fun removeQuestion(examCode: String, questionCode: String)

    fun getQuestionsByExamCode(examCode: String): ExamQuestionsResponse

    fun modifyQuestion(
        examCode: String,
        questionCode: String,
        questionText: String?,
        questionType: QuestionType?,
        questionImage: String?
    ): EditQuestionResponse

    fun getQuestionByCode(code: String): Question

    fun getAllQuestions(): List<Question>
}
