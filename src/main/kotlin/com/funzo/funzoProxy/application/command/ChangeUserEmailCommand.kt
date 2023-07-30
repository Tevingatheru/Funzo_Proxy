package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.GetUserDto

class ChangeUserEmailCommand(
    val userCode: String,
    val email: String
) : Command<GetUserDto>
