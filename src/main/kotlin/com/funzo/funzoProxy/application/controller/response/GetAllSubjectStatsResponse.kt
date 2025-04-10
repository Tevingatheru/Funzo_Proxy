package com.funzo.funzoProxy.application.controller.response

data class GetAllSubjectStatsResponse(
    val subjectCount: Long,
    val examCountPerSubject: List<ExamCountPerSubject>
)

data class ExamCountPerSubject(
    val subjectName: String,
    val examCount: Long
)
