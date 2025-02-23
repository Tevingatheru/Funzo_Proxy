package com.funzo.funzoProxy.domain.exam

import org.springframework.stereotype.Service

@Service
interface ExamService {
    fun findByCode(examCode: String): Exam

    fun deleteByCode(examCode: String)

    fun findExamsBySubjectCode(subjectCode: String): List<Exam>

    fun findAll(): List<Exam>

    fun save( subjectCode: String): Exam
}
