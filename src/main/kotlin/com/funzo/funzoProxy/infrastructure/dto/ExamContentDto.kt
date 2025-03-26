package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.question.Question

data class ExamContentDto(
    private var code: String? = null,
    private var totalQuestions: Int? = null,
    private var questions: MutableList<QuestionDto> = mutableListOf()
) {
    fun setCode(code: String) {
        this.code = code
    }

    fun setTotalNumberOfQuestions(examQuestions: MutableList<Question>) {
        totalQuestions =  examQuestions.size
    }

    fun setQuestions(examQuestions: MutableList<Question>) {
        TODO("Not yet implemented")
    }
}
