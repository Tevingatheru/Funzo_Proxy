package com.funzo.funzoProxy.infrastructure.dto

data class GetStudentStatsDto(
    var examResultMap: MutableList<Pair<String, Double>>,
    var overallAverage: Double
) {}
