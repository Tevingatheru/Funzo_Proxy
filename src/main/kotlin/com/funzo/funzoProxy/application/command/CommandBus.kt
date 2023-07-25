package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.command.bus.Command

interface CommandBus  {
    fun <R, C : Command<R>> dispatch(command: C): R
}