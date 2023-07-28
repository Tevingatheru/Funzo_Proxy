package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.infrastructure.dto.CreateSubjectDto

class CreateSubjectCommand(
    val name: String,
    val description: String,
    val category: String
): Command<CreateSubjectDto>
