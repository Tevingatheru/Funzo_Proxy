package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.CreateResultDto

class CreateResultCommand(
    val examCode: String,
    val userCode: String,
    val score: Double)
    : Command<CreateResultDto>
{

}
