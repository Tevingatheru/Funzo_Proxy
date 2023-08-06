package com.funzo.funzoProxy.infrastructure.dto

data class QuerylResultListDto(
    var resultList: List<QueryResultDto> = ArrayList()
) {
    fun add(queryResultDto: QueryResultDto) {
        resultList = resultList.plus(queryResultDto)
    }
}
