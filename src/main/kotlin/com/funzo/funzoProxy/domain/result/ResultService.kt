package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.infrastructure.dto.GetStatsDto

interface ResultService {
    fun createResult(examCode: String, userCode: String, score: Double): Result

    fun findByCode(code: String) : Result?

    fun findAll() : List<Result>

    fun findResultsByUserCode(userCode: String): List<Result>

    fun findResultsByExamCodeAndUserCode(examCode: String, userCode: String) : List<Result>

    fun deleteByCode(code: String)

    fun getStudentStatsByCode(userCode: String): GetStatsDto

    fun getTeacherStatsByUserCode(userCode: String): GetStatsDto
}
