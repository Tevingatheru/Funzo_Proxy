package com.funzo.funzoProxy.domain.result

interface ResultService {
    fun createResult(examCode: String, userCode: String, score: String): Result

    fun findByCode(code: String) : Result

    fun findAll() : List<Result>

    fun findResultsByUserCode(userCode: String): List<Result>

    fun findResultsByExamCodeAndUserCode(examCode: String, userCode: String) : List<Result>

    fun deleteByCode(code: String)
}
