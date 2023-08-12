package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.AddQuestionDto

class AddQuestionCommand(val examCode: String, val image: String?, val questionText: String)
    : Command<AddQuestionDto>
{

}
