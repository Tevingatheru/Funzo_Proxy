package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.Query

class QueryHandlerImpl<R, Q : Query<R>> : QueryHandler<R, Q> {
    override fun handle(query: Q): R {
        TODO("Not yet implemented")
    }
}