package com.funzo.funzoProxy.infrastructure.dto

data class AllSubjectStatsDto(
    val subjectCount: Long,
    val examCountPerSubject: List<Pair<String, Long>>
)
