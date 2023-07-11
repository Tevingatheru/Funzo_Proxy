package com.funzo.funzoProxy.infrastructure

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
class ExamRepositoryImpl : ExamRepository {
    private val exams: MutableMap<String?, Exam> = mutableMapOf()

    @Transactional
    override fun findByCode(examCode: String): Exam? {
        return exams[examCode]
    }

    @Transactional
    override fun save(exam: Exam) {
        exams[exam.code] = exam
    }
}
