package com.funzo.funzoProxy.application.command.bus

import com.funzo.funzoProxy.application.command.Command

/**
 * Interface representing a CommandBus. The CommandBus is responsible for dispatching commands to their respective
 * command handlers.
 */
interface CommandBus  {
    /**
     * Dispatches the given command to its corresponding command handler.
     * @param C The type of command to be dispatched.
     * @param R The return type of the command handler.
     * @param command The command to be dispatched.
     * @return The result of handling the command.
     */
    fun <R, C : Command<R>> dispatch(command: C): R
}