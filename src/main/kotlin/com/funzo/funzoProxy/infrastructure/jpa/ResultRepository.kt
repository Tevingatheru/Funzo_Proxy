package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.result.Result
import com.funzo.funzoProxy.infrastructure.jpa.projection.AverageScoreProjection
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


    @Query(
        """
        SELECT 
            r.exam.code AS examCode,
            r.exam.description AS examName,
            AVG(r.score) AS averageScore
        FROM Result r
        WHERE r.student.code = :studentCode
        GROUP BY r.exam.code, r.exam.description
    """
    )
    fun findAverageScoresByExam(@Param(value = "studentCode") studentCode: String): List<AverageScoreProjection>

    @Query(
        """
        SELECT 
            e.code AS examCode,
            e.description AS examName,
            AVG(r.score) AS averageScore
        FROM results r
        JOIN exams e on r.exam_code = e.code
        WHERE e.user_code = :teacherCode
        GROUP BY e.code, e.description
    """, nativeQuery = true)
    fun findAverageExamPerformanceByTeacherCode(@Param("teacherCode") teacherCode: String): List<AverageScoreProjection>


    @Query(
        """
        SELECT 
            e.code AS examCode,
            e.description AS examName,
            AVG(r.score) AS averageScore
        FROM results r
        JOIN exams e on r.exam_code = e.code        
        GROUP BY e.code
    """, nativeQuery = true)
    fun getAllExamsAveragePerformance(): List<AverageScoreProjection>
}
