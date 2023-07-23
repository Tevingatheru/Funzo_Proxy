package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.application.controller.response.CreateQuestionCommandResponse
import com.funzo.funzoProxy.domain.exam.QuestionType

data class CreateQuestionCommand(
    val questionText: String,
    val image: String,
    val questionType: QuestionType,
    val examCode: String?
): Command<CreateQuestionCommandResponse> {
    constructor(questionText: String, image: String, questionType: String) :
            this(questionText, image, QuestionType.find(questionType), null)
}