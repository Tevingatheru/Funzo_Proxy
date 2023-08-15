package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.OptionDto

class ModifyOptionCommand(
    val code: String,
    val optionA: String?,
    val optionB: String?,
    val optionC: String?,
    val optionD: String?,
    val correctOption: String
) :
    Command<OptionDto> {

}
