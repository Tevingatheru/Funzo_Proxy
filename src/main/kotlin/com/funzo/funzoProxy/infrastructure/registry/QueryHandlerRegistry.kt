package com.funzo.funzoProxy.infrastructure.registry

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import org.springframework.context.ApplicationContext
import org.springframework.core.GenericTypeResolver
import org.springframework.stereotype.Component

@Component
class QueryHandlerRegistry{
    private val queryProviderMap: MutableMap<Class<out Query<*>>, QueryHandlerProvider<*>> = HashMap()

    fun init (applicationContext: ApplicationContext) {
        val names: Array<String> = applicationContext.getBeanNamesForType(QueryHandler::class.java)
        for (name in names) {
            register(applicationContext, name)
        }
    }

    private fun register(applicationContext: ApplicationContext, name: String) {
        val queryHandlerClass = applicationContext.getType(name) as Class<QueryHandler<*, *>>
        val arrayOfQueryHandlers = GenericTypeResolver.resolveTypeArguments(queryHandlerClass, QueryHandler::class.java)
        val queryType = arrayOfQueryHandlers?.get(1) as Class<out Query<*>>
        queryProviderMap[queryType] = QueryHandlerProvider(applicationContext, queryHandlerClass)
    }

    operator fun <T, Q : Query<T>> get(queryClass: Class<Q>?): QueryHandler<T, Q > {
        return queryProviderMap[queryClass!!]!!.get() as QueryHandler<T, Q>
    }
}
