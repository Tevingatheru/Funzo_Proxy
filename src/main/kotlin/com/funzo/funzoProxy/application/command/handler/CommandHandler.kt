package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.Command

/**
 * Interface representing a CommandHandler. Defines the handle method for handling commands.
 */
interface CommandHandler<R, C : Command<R>> {
    /**
     * Handles the given command.
     * @param command The command to be handled.
     * @return The result of handling the command.
     */
    fun handle(command: C): R
}