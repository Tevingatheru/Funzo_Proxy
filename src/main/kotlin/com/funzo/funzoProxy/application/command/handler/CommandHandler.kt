package com.funzo.funzoProxy.application.command.handler


interface CommandHandler<R, C > {
    fun handle(command: C): R
}