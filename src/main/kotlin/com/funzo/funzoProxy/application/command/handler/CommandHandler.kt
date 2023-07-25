package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.Command


interface CommandHandler<R, C : Command<R>> {
    fun handle(command: C): R
}