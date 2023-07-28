package com.funzo.funzoProxy.application.query.bus

import com.funzo.funzoProxy.application.query.Query

/**
 * Interface representing a QueryBus. Defines the execute method to execute queries.
 */
interface QueryBus {
    /**
     * Executes the given query.
     * @param query The query to be executed.
     * @return The result of handling the query.
     */
    fun <R, Q : Query<R>> execute(query: Q): R
}