package com.funzo.funzoProxy.application.controller.response

data class CreateExamCommandResponse (
    val code: String,
    val userCode: String,
    val subject: String,
    val description: String,
)
