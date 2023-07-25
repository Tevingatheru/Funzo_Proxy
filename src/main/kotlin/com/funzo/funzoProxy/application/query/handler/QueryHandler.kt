package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.bus.Query

interface QueryHandler<R, out Q : Query<R>>
{
    fun handle(query:  @UnsafeVariance Q): R?
}