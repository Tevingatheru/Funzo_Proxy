package com.funzo.funzoProxy.infrastructure.dto

data class OptionDto(
    val code: String?,
    val questionCode: String?,
    val type: String,
    val optionA: String? = null,
    val optionB: String? = null,
    val correctOption: String?,
    val optionC: String? = null,
    val optionD: String? = null
) {

}
