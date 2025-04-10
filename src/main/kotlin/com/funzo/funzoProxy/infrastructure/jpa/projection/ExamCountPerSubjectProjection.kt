package com.funzo.funzoProxy.infrastructure.jpa.projection

interface ExamCountPerSubjectProjection {
    val subjectName: String?
    val examCount: Long?
}
