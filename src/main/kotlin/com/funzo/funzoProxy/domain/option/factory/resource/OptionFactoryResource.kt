package com.funzo.funzoProxy.domain.option.factory.resource

import com.funzo.funzoProxy.domain.question.Question

data class OptionFactoryResource(
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?,
    val correctOption: String?,
    val code: String,
    val question: Question
) {

}
