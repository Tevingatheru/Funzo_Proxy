package com.funzo.funzoProxy.infrastructure.dto

data class QueryResultListDto(
    var results: List<QueryResultDto> = ArrayList()
) {
    fun add(queryResultDto: QueryResultDto) {
        results = results.plus(queryResultDto)
    }
}
