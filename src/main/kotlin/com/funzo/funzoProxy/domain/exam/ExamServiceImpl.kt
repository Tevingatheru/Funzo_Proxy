package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import org.springframework.stereotype.Service

@Service
class ExamServiceImpl(private val examRepository: ExamRepository) : ExamService  {

    override fun findByCode(examCode: String): Exam? {
        return examRepository.findByCode(examCode)
    }

    override fun save(exam: Exam): Exam {
        return examRepository.save(exam)
    }
}
