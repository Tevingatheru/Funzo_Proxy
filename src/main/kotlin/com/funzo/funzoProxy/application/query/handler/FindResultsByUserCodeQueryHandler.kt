package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FindResultsByUserCodeQuery
import com.funzo.funzoProxy.application.mapper.ResultMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QueryResultListDto
import org.springframework.stereotype.Component

@Component
class FindResultsByUserCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<QueryResultListDto, FindResultsByUserCodeQuery> {
    override fun handle(query: FindResultsByUserCodeQuery): QueryResultListDto {
        return ResultMapper
            .mapToQueryAllResultsDto(
                resultService.findResultsByUserCode(userCode = query.userCode))
    }
}
