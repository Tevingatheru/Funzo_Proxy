package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command

data class CreateExamCommand(
    val level: Int,
    val questions: List<CreateQuestionCommand>
): Command
