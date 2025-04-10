package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByStudentCodeResponse
import com.funzo.funzoProxy.application.mapper.ResultMapper
import com.funzo.funzoProxy.application.query.GetResultsStatsByStudentCodeQuery
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.GetStatsDto
import org.springframework.stereotype.Component

@Component
class GetResultsStatsByStudentCodeQueryHandler(
    private val resultService: ResultService
) : QueryHandler<GetResultsStatsByStudentCodeResponse, GetResultsStatsByStudentCodeQuery> {

    override fun handle(query: GetResultsStatsByStudentCodeQuery): GetResultsStatsByStudentCodeResponse {
        val getStatsDto: GetStatsDto = resultService.getStudentStatsByCode(userCode = query.studentCode)
        return ResultMapper.mapDtoToStudentStatResponse(dto = getStatsDto)
    }
}
