package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FetchUserByCodeQuery
import com.funzo.funzoProxy.application.mapper.ResultMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QueryResultDto
import org.springframework.stereotype.Component

@Component
class FetchUserByCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<QueryResultDto?, FetchUserByCodeQuery> {
    override fun handle(query: FetchUserByCodeQuery): QueryResultDto? {
        val result = resultService.findByCode(query.code) ?: return null
        return ResultMapper.mapToQueryResultDto(result)
    }
}
