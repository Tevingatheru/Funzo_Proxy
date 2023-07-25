package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CommandBus
import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.infrastructure.CommandHandlerRegistry
import org.springframework.stereotype.Component

@Component
class CommandBusImpl(private val registry: CommandHandlerRegistry) : CommandBus {

    override fun <R, C : Command<R>> dispatch(command: C): R {
        val commandHandler: CommandHandler<R, C> = registry[command::class.java] as CommandHandler<R, C>
        return commandHandler.handle(command)
    }
}