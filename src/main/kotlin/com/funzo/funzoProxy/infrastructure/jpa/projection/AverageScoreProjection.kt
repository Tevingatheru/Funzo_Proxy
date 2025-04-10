package com.funzo.funzoProxy.infrastructure.jpa.projection

interface AverageScoreProjection {
    val examName: String?
    val examCode: String?
    val averageScore: Double?
}
