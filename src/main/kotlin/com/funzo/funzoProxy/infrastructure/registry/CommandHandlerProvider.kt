package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.command.handler.CommandHandler
import org.springframework.context.ApplicationContext

/**
 * A provider for Command Handlers. This class is responsible for retrieving instances of Command Handlers from the Spring ApplicationContext.
 *
 * @param H The type of the Command Handler.
 * @property applicationContext The Spring ApplicationContext used for retrieving instances of Command Handlers.
 * @property type The Class object representing the type of the Command Handler.
 */
class CommandHandlerProvider<H : CommandHandler<*, *>>(
    private val applicationContext: ApplicationContext,
    private val type: Class<H>
) {
    /**
     * Get an instance of the Command Handler.
     *
     * @return An instance of the Command Handler.
     */
    fun get(): H {
        return applicationContext.getBean(type)
    }
}
