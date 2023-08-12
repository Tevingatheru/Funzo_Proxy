package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.EditQuestionDto

class EditQuestionCommand(
    val examCode: String,
    val questionCode: String,
    val question: String?,
    val image: String?
): Command<EditQuestionDto> {

}