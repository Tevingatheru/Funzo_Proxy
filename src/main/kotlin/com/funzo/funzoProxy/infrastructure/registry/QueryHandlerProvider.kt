package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.query.handler.QueryHandler
import org.springframework.context.ApplicationContext

/**
 * A generic QueryHandlerProvider class that provides instances of QueryHandler implementations
 * based on their specific types.
 *
 * @param H The type of QueryHandler this provider can provide.
 */
class QueryHandlerProvider< H : QueryHandler<*, *>> (
    private val applicationContext: ApplicationContext,
    private val type: Class<H>
){
    /**
     * Retrieves an instance of the specified QueryHandler from the application context.
     *
     * @return An instance of the specified QueryHandler.
     */
    fun get(): H {
        return applicationContext.getBean(type)
    }
}

