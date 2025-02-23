package com.funzo.funzoProxy.application.controller.request

data class CreateExamRequest(
    val userCode: String,
    val subjectCode: String,
    val examDescription: String,
)
