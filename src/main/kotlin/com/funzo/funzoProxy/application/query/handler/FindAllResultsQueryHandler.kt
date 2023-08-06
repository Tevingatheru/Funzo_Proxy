package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.FindAllResultsQuery
import com.funzo.funzoProxy.application.mapper.ResultDtoMapper
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.QuerylResultListDto
import org.springframework.stereotype.Component

@Component
class FindAllResultsQueryHandler(
    private val resultService: ResultService
) : QueryHandler<QuerylResultListDto, FindAllResultsQuery>{
    override fun handle(query: FindAllResultsQuery): QuerylResultListDto {
        return ResultDtoMapper.mapToQueryAllResultsDto(resultService.findAll())
    }
}
