package com.funzo.funzoProxy.application.controller.response

data class GetResultsStatsByTeacherCodeResponse(
    val totalPerformanceAverage: Double,
    val examAverages: List <ExamAverageResponse>
)
