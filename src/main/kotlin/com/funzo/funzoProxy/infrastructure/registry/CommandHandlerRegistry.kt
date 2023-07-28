package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.command.Command
import com.funzo.funzoProxy.application.command.handler.CommandHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import org.springframework.stereotype.Component
import java.lang.RuntimeException


/**
 * Registry for Command Handlers. This class manages the mapping between command classes and their respective handlers.
 * It automatically discovers and registers Command Handlers using Spring's ApplicationContext.
 */
@Component
class CommandHandlerRegistry @Autowired constructor(
    applicationContext: ApplicationContext
) {
    private val providerMap = HashMap<Class<out Command<*>>, CommandHandlerProvider<*>>()

    /**
     * Initializes the CommandHandlerRegistry by discovering and registering Command Handlers from the ApplicationContext.
     * @param applicationContext The Spring ApplicationContext used for discovering Command Handlers.
     */
    init {
        val names: Array<String> = applicationContext.getBeanNamesForType(CommandHandler::class.java)
        for (name in names) {
            val commandHandlerClass = applicationContext.getType(name) as Class<CommandHandler<*, *>>
            register(applicationContext, commandHandlerClass)
        }
    }

    /**
     * Registers a Command Handler in the registry.
     * @param applicationContext The Spring ApplicationContext.
     * @param handler The Command Handler to be registered.
     */
    private fun register(applicationContext: ApplicationContext, handler:Class<CommandHandler<*, *>>) {
        val generics = GenericTypeResolver.resolveTypeArguments(handler::class.java, CommandHandler::class.java)
        val commandType = generics?.get(1) as? Class<out Command<*>?>
        commandType?.let { providerMap[it] = CommandHandlerProvider(applicationContext, handler::class.java as Class<CommandHandler<*, *>>) }
    }

    /**
     * Retrieves the Command Handler associated with the specified command class.
     * @param commandClass The command class for which the handler is to be retrieved.
     * @return The Command Handler for the specified command class.
     * @throws RuntimeException if no handler is found for the specified command class.
     */
    operator fun <R, C : Command<R>> get(commandClass: Class<out C>): CommandHandler<*, *> {
        return providerMap[commandClass]?.get() ?: throw RuntimeException("Handler not found for command: ${commandClass.simpleName}")
    }
}


