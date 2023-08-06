package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FetchResultsByExamCodeAndUserCodeQuery
import com.funzo.funzoProxy.application.mapper.ResultDtoMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QuerylResultListDto
import org.springframework.stereotype.Component

@Component
class FetchResultsByExamCodeAndUserCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<QuerylResultListDto, FetchResultsByExamCodeAndUserCodeQuery> {
    override fun handle(query: FetchResultsByExamCodeAndUserCodeQuery): QuerylResultListDto {
        return ResultDtoMapper
            .mapToQueryAllResultsDto(
                resultService.findResultsByExamCodeAndUserCode(examCode = query.examCode, userCode = query.userCode))
    }
}