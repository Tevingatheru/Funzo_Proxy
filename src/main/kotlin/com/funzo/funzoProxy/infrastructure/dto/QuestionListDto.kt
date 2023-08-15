package com.funzo.funzoProxy.infrastructure.dto

data class QuestionListDto(var questions: List<QuestionDto>? = mutableListOf<QuestionDto>()) {
    fun add(questionDto: QuestionDto) {
        questions = this.questions?.plus(QuestionDto(
            examCode = questionDto.examCode,
            text =  questionDto.text,
            questionType =  questionDto.questionType,
            code = questionDto.code
        ))
    }
}
