package com.funzo.funzoProxy.infrastructure.dto

data class GetStatsDto(
    var examResultMap: MutableList<Pair<String, Double>>,
    var overallAverage: Double
) {}
