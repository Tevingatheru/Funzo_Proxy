package com.funzo.funzoProxy.infrastructure.dto

data class QuestionContentDto(
    val examCode: String?,
    val questionType: String?,
    val text: String?,
    val code: String?,
    val option: OptionDto?) {

}
