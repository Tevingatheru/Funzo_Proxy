package com.funzo.funzoProxy.application.command.bus

import com.funzo.funzoProxy.application.command.Command

interface CommandBus  {
    fun <R, C : Command<R>> dispatch(command: C): R
}