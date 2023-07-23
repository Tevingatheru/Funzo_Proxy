package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.domain.exam.QuestionType

data class AddQuestionCommand(
    val questionText: String,
    val image: String?,
    val questionType: QuestionType,
    val examCode: String?
): Command<AddQuestionCommandResponse> {
    constructor(questionText: String, image: String?, questionType: String) :
            this(questionText, image, QuestionType.find(questionType), null)
}