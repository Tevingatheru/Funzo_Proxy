package com.funzo.funzoProxy.application.command.bus

import com.funzo.funzoProxy.application.command.Command
import com.funzo.funzoProxy.application.command.handler.CommandHandler
import com.funzo.funzoProxy.infrastructure.registry.CommandHandlerRegistry
import org.springframework.stereotype.Component

@Component
class CommandBusImpl(private val registry: CommandHandlerRegistry) : CommandBus {

    override fun <R, C : Command<R>> dispatch(command: C): R {
        val commandHandler: CommandHandler<R, C> = registry[command::class.java] as CommandHandler<R, C>
        return commandHandler.handle(command)
    }
}