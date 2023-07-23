package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.application.command.handler.CommandHandler
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import org.springframework.stereotype.Component

@Component
class CommandHandlerRegistry(applicationContext: ApplicationContext) {
    private val providerMap =
        HashMap<Class<out Command<*>?>, CommandHandlerProvider<*>>()

    init {
        val names: Array<String> = applicationContext.getBeanNamesForType(CommandHandler::class.java)
        for (name in names) {
            register(applicationContext, name)
        }
    }

    private fun register(applicationContext: ApplicationContext, name: String) {
        val handlerClass: Class<CommandHandler<*, *>> =
            (applicationContext.getType(name) as Class<CommandHandler<*, *>>?)!!
        val generics = GenericTypeResolver.resolveTypeArguments(
            handlerClass,
            CommandHandler::class.java
        )!!
        val commandType = generics[1] as Class<out Command<*>?>
        providerMap[commandType] = CommandHandlerProvider(applicationContext, handlerClass)
    }

    operator fun <R,  C : Command<R>?> get(commandClass: Class<C>): CommandHandler<R, Command<R>> {
        return providerMap.get(key = commandClass)?.get() as CommandHandler<R, Command<R>>
    }
}


