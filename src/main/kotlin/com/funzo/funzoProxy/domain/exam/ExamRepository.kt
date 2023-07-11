package com.funzo.funzoProxy.domain.exam


interface ExamRepository {
    fun findByCode(examCode: String): Exam?
    fun save(exam: Exam)

}
