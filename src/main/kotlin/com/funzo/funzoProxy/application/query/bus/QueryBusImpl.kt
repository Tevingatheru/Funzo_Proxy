package com.funzo.funzoProxy.application.query.bus

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.infrastructure.registry.QueryHandlerRegistry


class QueryBusImpl (
    private val queryHandlerRegistry: QueryHandlerRegistry
): QueryBus {

    override fun <R, Q : Query<R>> execute(query: Q): R {
        val queryHandler: QueryHandler<R, Q> =  queryHandlerRegistry.get(query.javaClass)
        return queryHandler.handle(query)!!
    }
}