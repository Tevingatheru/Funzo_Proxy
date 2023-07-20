package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.subject.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository: JpaRepository<Subject, Long> {
    fun deleteByCode(code: String): Subject
    fun findByCode(code: String): Subject
}