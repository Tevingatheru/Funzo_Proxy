package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.question.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository: JpaRepository<Question, Long> {
    fun findByCode(questionCode: String): Question
}