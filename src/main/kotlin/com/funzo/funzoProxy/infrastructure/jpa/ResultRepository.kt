package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.result.Result
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ResultRepository: JpaRepository<Result, Long> {
    fun deleteByCode(code: String)

    fun findByCode(code: String): Result

    @Query(value = "SELECT * FROM results WHERE exam_code = :emailCode AND student_code = :userCode",
        nativeQuery = true)
    fun findByExamCodeAndUserCode(@Param(value = "emailCode") emailCode: String,
                                  @Param(value = "userCode") userCode: String): List<Result>

    @Query(value = "SELECT * FROM results WHERE student_code = :userCode",
        nativeQuery = true)
    fun findByUserCode(@Param(value = "userCode") userCode: String): List<Result>


//    @Query("SELECT exam.code, AVG(r.score) FROM Result r JOIN r.exam e GROUP BY e.code")
//    fun findAverageScoresByExam(): List<AverageScoreProjection>
}


