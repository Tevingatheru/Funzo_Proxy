package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.command.handler.CommandHandler
import org.springframework.context.ApplicationContext


@JvmRecord
internal data class CommandHandlerProvider< P : CommandHandler<*, *>?>(
    val applicationContext: ApplicationContext,
    val type: Class<@UnsafeVariance P>
) {
    fun get(): P {
        return applicationContext.getBean(type)
    }
}

