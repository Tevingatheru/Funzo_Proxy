package com.funzo.funzoProxy.application.controller.request

data class CreateExamRequest(
    val level: Int,
    val questions: List<CreateQuestionRequest>
)

