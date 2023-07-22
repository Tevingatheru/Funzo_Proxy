package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command

class CreateSubjectCommand(
    val name: String,
    val description: String,
    val category: String
): Command
