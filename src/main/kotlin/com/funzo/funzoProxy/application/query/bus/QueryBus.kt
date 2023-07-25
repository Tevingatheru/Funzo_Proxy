package com.funzo.funzoProxy.application.query.bus

interface QueryBus {
    fun <R, Q : Query<R>> execute(query: Q): R
}