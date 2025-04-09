package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.application.controller.response.ExamAverageResponse
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByStudentCodeResponse
import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByTeacherCodeResponse
import com.funzo.funzoProxy.domain.result.Result
import com.funzo.funzoProxy.infrastructure.dto.*

object ResultMapper {
    fun mapToQueryResultDto(result: Result): QueryResultDto {
        return QueryResultDto(
            attemptNo = result.attemptNo,
            code = result.code!!,
            score = result.score!!
        )
    }

    fun mapToCreateResultDto(result: Result): CreateResultDto {
        return CreateResultDto(
            attemptNo = result.attemptNo,
            code = result.code!!,
            score = result.score!!
        )
    }

    fun mapToQueryAllResultsDto(results: List<Result>): QueryResultListDto {
        val queryResultListDto = QueryResultListDto()
        results.forEach{
            val resultDto = this.mapToQueryResultDto(result = it)
            queryResultListDto.add(queryResultDto = resultDto)
        }
        return queryResultListDto
    }

    fun mapDtoToStudentStatResponse(dto: GetStatsDto): GetResultsStatsByStudentCodeResponse {
        val examResultMap = dto.examResultMap

        val getResultsStatsByStudentCodeResponse: GetResultsStatsByStudentCodeResponse = GetResultsStatsByStudentCodeResponse(
            overallAverage = dto.overallAverage,
            examAverages = mapExamAverageResponsesToList(examResultMap)
        )
        return getResultsStatsByStudentCodeResponse
    }

    fun mapDtoToTeacherStatResponse(dto: GetStatsDto): GetResultsStatsByTeacherCodeResponse {
        val examResultMap = dto.examResultMap

        val getResultsStatsByStudentCodeResponse: GetResultsStatsByTeacherCodeResponse = GetResultsStatsByTeacherCodeResponse(
            totalPerformanceAverage = dto.overallAverage,
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
