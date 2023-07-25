package com.funzo.funzoProxy.application.query.bus

import com.funzo.funzoProxy.application.query.Query

interface QueryBus {
    fun <R, Q : Query<R>> execute(query: Q): R
}