package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FindResultsByUserCodeQuery
import com.funzo.funzoProxy.application.mapper.ResultDtoMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QuerylResultListDto
import org.springframework.stereotype.Component

@Component
class FindResultsByUserCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<QuerylResultListDto, FindResultsByUserCodeQuery> {
    override fun handle(query: FindResultsByUserCodeQuery): QuerylResultListDto {
        return ResultDtoMapper
            .mapToQueryAllResultsDto(
                resultService.findResultsByUserCode(userCode = query.userCode))
    }
}
