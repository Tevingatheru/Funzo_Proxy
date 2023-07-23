package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command

class UpdateSubjectCommand(
    val name: String, val category: String, val description: String, val code: String): Command {

}
