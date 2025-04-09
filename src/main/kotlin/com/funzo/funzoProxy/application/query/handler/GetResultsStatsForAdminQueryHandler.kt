package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByAdminCodeResponse
import com.funzo.funzoProxy.application.mapper.ResultMapper
import com.funzo.funzoProxy.application.query.GetResultsStatsByAdminCodeQuery
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.AllExamStatsDto
import org.springframework.stereotype.Component

@Component
class GetResultsStatsForAdminQueryHandler(
    private val resultService: ResultService
): QueryHandler<GetResultsStatsByAdminCodeResponse, GetResultsStatsByAdminCodeQuery> {
    override fun handle(query: GetResultsStatsByAdminCodeQuery): GetResultsStatsByAdminCodeResponse {
        val results: AllExamStatsDto = resultService.getAllExamStats(adminCode = query.adminCode)
        return mapToGetResultsStatsByAdminCodeResponse(results)
    }

    private fun mapToGetResultsStatsByAdminCodeResponse(results: AllExamStatsDto): GetResultsStatsByAdminCodeResponse {
        return GetResultsStatsByAdminCodeResponse(ResultMapper.mapExamAverageResponsesToList(results.examResultMap))
    }
}