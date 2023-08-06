package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FetchResultsByExamCodeAndUserCodeQuery
import com.funzo.funzoProxy.application.mapper.ResultDtoMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QueryResultListDto
import org.springframework.stereotype.Component

@Component
class FetchResultsByExamCodeAndUserCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<QueryResultListDto, FetchResultsByExamCodeAndUserCodeQuery> {
    override fun handle(query: FetchResultsByExamCodeAndUserCodeQuery): QueryResultListDto {
        return ResultDtoMapper
            .mapToQueryAllResultsDto(
                resultService.findResultsByExamCodeAndUserCode(examCode = query.examCode, userCode = query.userCode))
    }
}