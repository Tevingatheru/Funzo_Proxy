package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.exam.Exam

data class TeachersExamListDto(var exams: List<TeacherExamDto>? = arrayListOf()) {

    fun add(exam: Exam) {
        val examDto = TeacherExamDto(
            examCode = exam.code,
            subject = exam.subject!!.name!!,
            description= exam.description
            )
        this.exams = exams!!.plus(examDto)
    }
}
