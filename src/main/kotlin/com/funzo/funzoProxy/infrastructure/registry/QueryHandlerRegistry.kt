package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import org.springframework.stereotype.Component
import java.lang.RuntimeException

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

    private fun register(applicationContext: ApplicationContext, queryHandlerClass: Class<QueryHandler<*, *>>) {
        val arrayOfQueryHandlers = GenericTypeResolver.resolveTypeArguments(queryHandlerClass, QueryHandler::class.java)
        val queryType = arrayOfQueryHandlers?.get(1) as Class<out Query<*>>
        queryProviderMap[queryType] = QueryHandlerProvider(applicationContext, queryHandlerClass)
    }

    operator fun <T, Q : Query<T>> get(queryClass: Class<out Q>): QueryHandler<*, *> {
        return queryProviderMap[queryClass]?.get() ?: throw RuntimeException("Handler not found for query: ${queryClass.simpleName}")
    }
}
