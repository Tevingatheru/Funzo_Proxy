package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import org.springframework.stereotype.Component
import java.lang.RuntimeException

/**
 * A registry for Query Handlers. This class manages the mapping between query classes and their respective handlers.
 * It automatically discovers and registers Query Handlers using Spring's ApplicationContext.
 *
 * @param applicationContext The Spring ApplicationContext used for discovering Query Handlers.
 */
@Component
class QueryHandlerRegistry(
    @Autowired applicationContext: ApplicationContext
){

    private val queryProviderMap: MutableMap<Class<out Query<*>>, QueryHandlerProvider<*>> = HashMap()

    init  {
        val names: Array<String> = applicationContext.getBeanNamesForType(QueryHandler::class.java)
        for (name in names) {
            val queryHandlerClass = applicationContext.getType(name) as Class<QueryHandler<*, *>>
            register(applicationContext, queryHandlerClass)
        }
    }

    /**
     * Register a Query Handler in the registry.
     *
     * @param applicationContext The Spring ApplicationContext.
     * @param queryHandlerClass The Class object representing the Query Handler to be registered.
     */
    private fun register(applicationContext: ApplicationContext, queryHandlerClass: Class<QueryHandler<*, *>>) {
        val arrayOfQueryHandlers = GenericTypeResolver.resolveTypeArguments(queryHandlerClass, QueryHandler::class.java)
        val queryType = arrayOfQueryHandlers?.get(1) as Class<out Query<*>>
        queryProviderMap[queryType] = QueryHandlerProvider(applicationContext, queryHandlerClass)
    }

    /**
     * Retrieve the Query Handler associated with the specified query class.
     *
     * @param queryClass The query class for which the handler is to be retrieved.
     * @return The Query Handler for the specified query class.
     * @throws RuntimeException if no handler is found for the specified query class.
     */
    operator fun <T, Q : Query<T>> get(queryClass: Class<out Q>): QueryHandler<*, *> {
        return queryProviderMap[queryClass]?.get() ?: throw RuntimeException("Handler not found for query: ${queryClass.simpleName}")
    }
}
