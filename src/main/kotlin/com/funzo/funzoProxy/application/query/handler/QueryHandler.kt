package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.Query

/**
 * Interface representing a QueryHandler. Defines the handle method for handling queries.
 */
interface QueryHandler<R, Q : Query<R>>
{
    /**
     * Handles the given query.
     * @param query The query to be handled.
     * @return The result of handling the query.
     */
    fun handle(query: Q): R
}