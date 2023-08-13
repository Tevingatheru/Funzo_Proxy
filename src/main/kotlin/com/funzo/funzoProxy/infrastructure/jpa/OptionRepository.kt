package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.option.Option
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OptionRepository: JpaRepository<Option, Long> {
    @Query(value = "DELETE FROM options WHERE options.code = :code",
        nativeQuery = true)
    @Modifying
    fun deleteByCode(@Param("code") code: String)

    fun getByCode(code: String): Option

    fun getByQuestionCode(questionCode: String): List<Option>

    @Query(value = "DELETE FROM multiple_choice_options WHERE multiple_choice_options.option_id = :id",
        nativeQuery = true)
    @Modifying
    fun deleteMultipleChoiceOptionId(@Param("id") id: Long)

    @Query(value = "DELETE FROM true_or_false_options WHERE true_or_false_options.option_id = :id",
        nativeQuery = true)
    @Modifying
    fun deleteTrueOrFalseOptionByOptionId(@Param("id") id: Long)
}
