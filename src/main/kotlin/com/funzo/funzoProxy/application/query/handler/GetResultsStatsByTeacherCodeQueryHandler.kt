package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByTeacherCodeResponse
import com.funzo.funzoProxy.application.mapper.ResultMapper
import com.funzo.funzoProxy.application.query.GetResultsStatsByTeacherCodeQuery
import com.funzo.funzoProxy.domain.result.ResultService
import org.springframework.stereotype.Component

@Component
class GetResultsStatsByTeacherCodeQueryHandler(
    private val resultService: ResultService
): QueryHandler<GetResultsStatsByTeacherCodeResponse, GetResultsStatsByTeacherCodeQuery> {

    override fun handle(query: GetResultsStatsByTeacherCodeQuery): GetResultsStatsByTeacherCodeResponse {
        val getStatsDto = resultService.getTeacherStatsByUserCode(userCode = query.teacherCode)
        return ResultMapper.mapDtoToTeacherStatResponse(getStatsDto)
    }
}
