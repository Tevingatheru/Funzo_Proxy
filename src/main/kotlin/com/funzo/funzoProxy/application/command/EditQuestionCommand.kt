package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.controller.response.EditQuestionCommandResponse

data class EditQuestionCommand(
    val examCode: String,
    val questionCode: String,
    val question: String?,
    val questionType: String,
    val image: String?
): Command<EditQuestionCommandResponse>