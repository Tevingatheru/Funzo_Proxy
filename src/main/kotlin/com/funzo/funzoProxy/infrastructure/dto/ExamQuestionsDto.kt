package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.question.Question

class ExamQuestionsDto (

) {

    val questions: MutableList<AllExamQuestionsDTO> = mutableListOf()
    constructor(questionList: List<Question>) : this() {
        questionList.forEach { question: Question ->
            val allExamQuestionsDTO = AllExamQuestionsDTO(
                question.code!!,
                question.question
            )
            this.questions.add(allExamQuestionsDTO)
        }
    }
}
