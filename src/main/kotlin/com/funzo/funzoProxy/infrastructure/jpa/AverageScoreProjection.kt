package com.funzo.funzoProxy.infrastructure.jpa

import lombok.ToString

@ToString
interface AverageScoreProjection {
    val examName: String?
    val examCode: String?
    val averageScore: Double?
}