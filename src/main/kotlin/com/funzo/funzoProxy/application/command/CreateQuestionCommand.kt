package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.domain.question.AddQuestionResponse

data class CreateQuestionCommand(
    val questionText: String,
    val image: String,
    val questionType: String,
    val examCode: String?,
    val correctOption: String,
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?
): Command<AddQuestionResponse> {
}
