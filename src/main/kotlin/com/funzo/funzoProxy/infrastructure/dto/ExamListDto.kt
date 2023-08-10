package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.exam.Exam

data class ExamListDto(var exams: List<ExamDto>? = arrayListOf()) {

    fun add(exam: Exam) {
        val examDto = ExamDto(
            exam.code,
            exam.subject!!.name!!,
            exam.level,
        )
        this.exams = exams!!.plus(examDto)
    }
}
