package com.funzo.funzoProxy.application.controller.request

data class UpdateSubjectRequest(
    val description: String,
    val category: String,
    val name: String,
    val code: String
)
