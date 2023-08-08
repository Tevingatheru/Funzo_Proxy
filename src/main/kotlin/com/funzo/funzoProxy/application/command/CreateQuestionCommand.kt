package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.controller.response.CreateQuestionCommandResponse
import com.funzo.funzoProxy.domain.option.Option

data class CreateQuestionCommand(
    val questionText: String,
    val image: String,
    val option: Option,
    val examCode: String?
): Command<CreateQuestionCommandResponse> {
    constructor(questionText: String, image: String, questionType: String) :
            this(questionText, image, Option.find(questionType), null)
}