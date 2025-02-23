package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse

data class CreateExamCommand(
    val subjectCode: String,
    val userCode: String,
    val examDescription: String,
): Command<CreateExamCommandResponse>
