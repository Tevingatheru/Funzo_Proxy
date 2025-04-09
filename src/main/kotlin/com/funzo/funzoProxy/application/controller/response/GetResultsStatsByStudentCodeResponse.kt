package com.funzo.funzoProxy.application.controller.response

data class GetResultsStatsByStudentCodeResponse (
    val overallAverage: Double,
    val examAverages: List <ExamAverageResponse>
)

