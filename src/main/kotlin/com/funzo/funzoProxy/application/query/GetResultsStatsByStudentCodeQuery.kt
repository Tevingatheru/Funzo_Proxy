package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByStudentCodeResponse

class GetResultsStatsByStudentCodeQuery(val studentCode: String) :
    Query<GetResultsStatsByStudentCodeResponse> {}
