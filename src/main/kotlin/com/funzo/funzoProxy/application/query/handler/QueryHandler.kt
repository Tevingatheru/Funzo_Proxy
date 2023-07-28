package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.Query

interface QueryHandler<R, Q : Query<R>>
{
    fun handle(query: Q): R?
}