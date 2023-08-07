package com.funzo.funzoProxy.application.query.bus

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.infrastructure.registry.QueryHandlerRegistry
import org.springframework.stereotype.Component

/**
 * Implementation of the QueryBus interface. Responsible for executing queries by invoking the appropriate query handler.
 */
@Component
class QueryBusImpl (
    private val queryHandlerRegistry: QueryHandlerRegistry
): QueryBus {
    /**
     * Executes the given query by invoking its corresponding query handler.
     * @param query The query to be executed.
     * @return The result of handling the query.
     * @throws RuntimeException if no handler is found for the specified query.
     */
    override fun <R, Q : Query<R>> execute(query: Q): R {
        val queryHandler =  queryHandlerRegistry.get(query::class.java) as QueryHandler<R, Q>
        return queryHandler.handle(query)!!
    }
}
