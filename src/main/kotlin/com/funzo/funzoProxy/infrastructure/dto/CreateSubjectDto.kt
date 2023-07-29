package com.funzo.funzoProxy.infrastructure.dto

data class CreateSubjectDto(
    val category: String,
    val code: String,
    val description: String,
    val name: String) {

}
