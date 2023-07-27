package com.funzo.funzoProxy.domain.question

class ExamQuestionsResponse {
    val allExamQuestionsDTOList = mutableListOf<AllExamQuestionsDTO>()

    constructor(questions: MutableList<Question>?) {
        questions?.forEach { question: Question ->
            val allExamQuestionsDTO = AllExamQuestionsDTO(
                question.code!!,
                question.question
            )
            allExamQuestionsDTOList.add(allExamQuestionsDTO)
        }
    }
}
