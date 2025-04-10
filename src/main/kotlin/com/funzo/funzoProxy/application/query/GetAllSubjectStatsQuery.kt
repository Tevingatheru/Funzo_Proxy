package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.application.controller.response.GetAllSubjectStatsResponse

data class GetAllSubjectStatsQuery(
    val adminCode: String
): Query<GetAllSubjectStatsResponse>
