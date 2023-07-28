package com.funzo.funzoProxy.domain.exam

import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
interface ExamService {
    fun findByCode(examCode: String): Exam?
    fun save(exam: Exam): Exam

}
