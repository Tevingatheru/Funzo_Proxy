package com.funzo.funzoProxy.infrastructure.jpa

interface AverageScoreProjection {
    val examCode: String?
    val averageScore: Double?
}