package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.domain.result.Result
import com.funzo.funzoProxy.infrastructure.dto.*

object ResultDtoMapper {
    fun mapToQueryResultDto(result: Result): QueryResultDto {
        return QueryResultDto(
            attemptNo = result.attemptNo,
            code = result.code,
            score = result.score
        )
    }

    fun mapToCreateResultDto(result: Result): CreateResultDto {
        return CreateResultDto(
            attemptNo = result.attemptNo,
            code = result.code,
            score = result.score
        )
    }

    fun mapToQueryAllResultsDto(results: List<Result>): QuerylResultListDto {
        val queryResultListDto = QuerylResultListDto()
        results.forEach{
            val resultDto = this.mapToQueryResultDto(result = it)
            queryResultListDto.add(queryResultDto = resultDto)
        }
        return queryResultListDto
    }

}
