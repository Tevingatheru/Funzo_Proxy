package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.OptionDto

class CreateOptionCommand(
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?,
    val correctOption: String,
    val questionCode: String
) : Command<OptionDto> {

}
