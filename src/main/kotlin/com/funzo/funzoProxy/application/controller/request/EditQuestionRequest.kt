package com.funzo.funzoProxy.application.controller.request

class EditQuestionRequest(
    val examCode: String,
    val code: String,
    val question: String?,
    val questionType: String,
    val image: String?
) {

}