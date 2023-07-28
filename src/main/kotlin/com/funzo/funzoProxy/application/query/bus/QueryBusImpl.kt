package com.funzo.funzoProxy.application.query.bus

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.infrastructure.registry.QueryHandlerRegistry
import org.springframework.stereotype.Component

@Component
class QueryBusImpl (
    private val queryHandlerRegistry: QueryHandlerRegistry
): QueryBus {

    override fun <R, Q : Query<R>> execute(query: Q): R {
        val queryHandler =  queryHandlerRegistry.get(query::class.java) as QueryHandler<R, Q>
        return queryHandler.handle(query)!!
    }
}
