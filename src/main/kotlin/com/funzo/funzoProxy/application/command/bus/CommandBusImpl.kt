package com.funzo.funzoProxy.application.command.bus

import com.funzo.funzoProxy.application.command.Command
import com.funzo.funzoProxy.application.command.handler.CommandHandler
import com.funzo.funzoProxy.infrastructure.registry.CommandHandlerRegistry
import org.springframework.stereotype.Component

/**
 * Implementation of the CommandBus interface. Responsible for dispatching commands to their respective command handlers.
 */
@Component
class CommandBusImpl(private val commandHandlerRegistry: CommandHandlerRegistry) : CommandBus {

    /**
     * Dispatches the given command to its corresponding command handler.
     * @param command The command to be dispatched.
     * @return The result of handling the command.
     */
    override fun <R, C : Command<R>> dispatch(command: C): R {
        val commandHandler: CommandHandler<R, C> = commandHandlerRegistry.get(command::class.java) as CommandHandler<R, C>
        return commandHandler.handle(command)
    }
}