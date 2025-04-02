package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.ExamAverageResponse
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByStudentCodeResponse
import com.funzo.funzoProxy.application.query.GetResultsStatsByStudentCodeQuery
import com.funzo.funzoProxy.domain.result.ResultService
import com.funzo.funzoProxy.infrastructure.dto.GetStudentStatsDto
import org.springframework.stereotype.Component

@Component
class GetResultsStatsByStudentCodeQueryHandler(
    private val resultService: ResultService
) : QueryHandler<GetResultsStatsByStudentCodeResponse, GetResultsStatsByStudentCodeQuery> {

    override fun handle(query: GetResultsStatsByStudentCodeQuery): GetResultsStatsByStudentCodeResponse {
        val getStudentStatsDto: GetStudentStatsDto = resultService.getStudentStatsByCode(userCode = query.studentCode)
        return mapDtoToResponse(dto = getStudentStatsDto)
    }

    private fun mapDtoToResponse(dto: GetStudentStatsDto): GetResultsStatsByStudentCodeResponse {
        val examResultMap = dto.examResultMap

        val getResultsStatsByStudentCodeResponse: GetResultsStatsByStudentCodeResponse = GetResultsStatsByStudentCodeResponse(
            overallAverage = dto.overallAverage,
            examAverages = mapExamAverageResponsesToList(examResultMap)
        )
        return getResultsStatsByStudentCodeResponse
    }

    private fun mapExamAverageResponsesToList(examResultMap: MutableList<Pair<String, Double>>): MutableList<ExamAverageResponse> {
        val examAveragesList: MutableList<ExamAverageResponse> = mutableListOf()
        examResultMap.forEach {
            val examAverageResponse: ExamAverageResponse = ExamAverageResponse(
                examName = it.first,
                averageScoreOfTotalAttempts = it.second
            )
            examAveragesList.add(examAverageResponse)
        }
        return examAveragesList
    }
}
