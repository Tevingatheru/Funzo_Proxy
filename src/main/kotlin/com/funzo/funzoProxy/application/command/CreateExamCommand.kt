package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse

data class CreateExamCommand(
    val level: Int,
    val questions: List<CreateQuestionCommand>
): Command<CreateExamCommandResponse>
