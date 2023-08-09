package com.funzo.funzoProxy.application.controller.response

data class CreateExamCommandResponse (
    val code: String,
    val level: Int,
    val subject: String?
)
