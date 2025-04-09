package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.application.controller.response.GetResultsStatsByTeacherCodeResponse

data class GetResultsStatsByTeacherCodeQuery(val teacherCode :String): Query<GetResultsStatsByTeacherCodeResponse>
