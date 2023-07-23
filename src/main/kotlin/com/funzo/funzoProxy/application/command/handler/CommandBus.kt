package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.bus.Command
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class CommandBus(private val handlers: Map<KClass<out Command>, CommandHandler<out Command>>) {

    fun <C : Command> dispatch(command: C) {
        val handler = handlers[command::class] as? CommandHandler<C>
            ?: throw IllegalArgumentException("No handler found for command: ${command::class.simpleName}")
        handler.handle(command)
    }
}
