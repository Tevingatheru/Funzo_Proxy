package com.funzo.funzoProxy.application.controller.request

data class CreateQuestionRequest(
    val questionText: String,
    val questionType: String,
    val image: String
)