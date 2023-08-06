package com.funzo.funzoProxy.infrastructure.dto

data class QueryResultListDto(
    var resultList: List<QueryResultDto> = ArrayList()
) {
    fun add(queryResultDto: QueryResultDto) {
        resultList = resultList.plus(queryResultDto)
    }
}
