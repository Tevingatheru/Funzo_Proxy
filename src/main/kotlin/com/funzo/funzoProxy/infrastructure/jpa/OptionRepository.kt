package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.option.Option
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OptionRepository: JpaRepository<Option, Long> {
    fun deleteByCode(code: String)

    fun getByCode(code: String): Option

    fun getByQuestionCode(questionCode: String): List<Option>

}
