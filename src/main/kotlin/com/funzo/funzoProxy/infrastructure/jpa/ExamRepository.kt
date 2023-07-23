package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.exam.Exam
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExamRepository: JpaRepository<Exam, Long> {
    fun findByCode(examCode: String): Exam?
    fun deleteByCode(examCode: String)
}