package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.domain.question.EditQuestionResponse

class EditQuestionCommand(
    val examCode: String,
    val questionCode: String,
    val question: String?,
    val image: String?
): Command<EditQuestionResponse> {

}