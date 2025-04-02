package com.funzo.funzoProxy.application.controller.response

data class GetResultsStatsByStudentCodeResponse (
    private val overallAverage: Double,
    private val examAverages: List <ExamAverageResponse>
)

data class ExamAverageResponse(private val examName: String, private val averageScoreOfTotalAttempts: Double)
