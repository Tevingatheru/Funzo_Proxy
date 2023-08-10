package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse

data class CreateExamCommand(
    val level: Int,
    val subjectCode: String
): Command<CreateExamCommandResponse>
