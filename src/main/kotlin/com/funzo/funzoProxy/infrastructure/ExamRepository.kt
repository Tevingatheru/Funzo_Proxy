package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.domain.exam.Exam


interface ExamRepository {
    fun findByCode(examCode: String): Exam?
    fun save(exam: Exam)

}
