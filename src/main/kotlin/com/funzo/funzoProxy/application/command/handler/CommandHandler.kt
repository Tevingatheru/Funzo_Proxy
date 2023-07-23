package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.bus.Command

interface CommandHandler<T : Command> {
    fun handle(command: T)
}