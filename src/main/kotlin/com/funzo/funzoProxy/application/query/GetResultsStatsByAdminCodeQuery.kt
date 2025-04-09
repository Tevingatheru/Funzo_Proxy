package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByAdminCodeResponse

data class GetResultsStatsByAdminCodeQuery(val adminCode: String): Query<GetResultsStatsByAdminCodeResponse>
