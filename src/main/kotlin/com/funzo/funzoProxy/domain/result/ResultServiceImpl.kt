package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.dto.GetStatsDto
import com.funzo.funzoProxy.infrastructure.jpa.AverageScoreProjection
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.ResultRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private const val TAG : String = "ResultServiceImpl"

@Service
@Transactional
class ResultServiceImpl(
    @Autowired private val resultRepository: ResultRepository,
    @Autowired private val examRepository: ExamRepository,
    @Autowired private val userRepository: UserRepository,
    private val generateCodeServiceImpl: GenerateCodeServiceImpl,
) : ResultService {
    companion object{
        const val UNABLE_TO_FIND_RESULTS = "Unable to find results"
    }

    override fun createResult(examCode: String, userCode: String, score: Double): Result {
        try {
            var result = Result(
                exam = examRepository.findByCode(examCode = examCode) ?: throw NotFoundException(),
                student = userRepository.findStudentByUserCode(userCode),
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                score = score
            )

            val existingResultList = resultRepository.findByExamCodeAndUserCode(examCode, userCode)


            attemptNoIncrement(existingResultList, result)

            return resultRepository.saveAndFlush(result)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findByCode(code: String): Result? {
        return try {
            resultRepository.findByCode(code = code)
        } catch (e: EmptyResultDataAccessException) {
            return null
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.WARN, "Unable to find result by code: $code", this::class.java)
            throw RuntimeException(e)
        }
    }

    override fun findAll(): List<Result> {
        return try{
            resultRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException(UNABLE_TO_FIND_RESULTS, e)
        }
    }

    override fun findResultsByUserCode(userCode: String): List<Result> {
        return try {
            resultRepository.findByUserCode(userCode = userCode)
        } catch (e: Exception) {
            throw RuntimeException("Unable to find Result by userCode: $userCode", e)
        }
    }

    override fun findResultsByExamCodeAndUserCode(examCode: String, userCode: String): List<Result> {
        return try {
            resultRepository.findByExamCodeAndUserCode(userCode = userCode, emailCode = examCode)
        } catch (e: Exception) {
            throw RuntimeException("Unable to find result with examCode: $examCode and userCode: $userCode", e)
        }
    }

    override fun deleteByCode(code: String) {
        try {
            resultRepository.deleteByCode(code = code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to delete result with code: $code", e)
        }
    }

    override fun getStudentStatsByCode(userCode: String): GetStatsDto {
        val mapExamToResult: MutableList<Pair<String, Double>> = mutableListOf()

        val averageScoresByExam = resultRepository.findAverageScoresByExam(studentCode = userCode)
        LoggerUtils.log(
            LogLevel.INFO,
            message = "getStudentStatsByCode",
            className = this::class.java,
            diagnosisMap = mapOf(
                Pair("averageScoresByExam",     averageScoresByExam.joinToString(separator = "\n ", prefix = "[", postfix = "]") {
                    "examCode='${it.examCode}', examName='${it.examName}', averageScore=${it.averageScore}"
                })
            )
        )

        mapAverageScoreProjectionToDto(averageScoresByExam, mapExamToResult)

        val totalNumberOfAttemptedExams : Int = averageScoresByExam.size
        val totalAverageOfAttemptedExams  = averageScoresByExam.sumOf { it.averageScore!! }
        val totalAverage : Double = totalAverageOfAttemptedExams / totalNumberOfAttemptedExams

        val getStatsDto: GetStatsDto = GetStatsDto(
            examResultMap = mapExamToResult,
            overallAverage = totalAverage
        )

        return getStatsDto
    }

    override fun getTeacherStatsByUserCode(userCode: String): GetStatsDto {
        val teacherResults = resultRepository.findAverageExamPerformanceByTeacherCode(teacherCode = userCode)
        var examResultMap: MutableList<Pair<String, Double>> = mutableListOf()

        val totalNumberOfAttemptedExams : Int = teacherResults.size
        val totalAverageOfAttemptedExams  = teacherResults.sumOf { it.averageScore!! }
        val totalAverage : Double = totalAverageOfAttemptedExams / totalNumberOfAttemptedExams

        mapAverageScoreProjectionToDto(teacherResults, examResultMap)

        return GetStatsDto(
            examResultMap = examResultMap,
            overallAverage = totalAverage
        )
    }

    private fun mapAverageScoreProjectionToDto(
        teacherResults: List<AverageScoreProjection>,
        examResultMap: MutableList<Pair<String, Double>>
    ) {
        teacherResults.forEach {
            examResultMap.add(Pair(it.examName!!, it.averageScore!!))
        }
    }

    private fun attemptNoIncrement(
        existingResultList: List<Result>,
        result: Result
    ) {
        try {
            var currentHighestAttemptNumber : Int = 0
            if (existingResultList.isEmpty()) {
                currentHighestAttemptNumber = 0
            } else {
                currentHighestAttemptNumber = existingResultList.size
            }
            result.attemptNo = currentHighestAttemptNumber + 1
        } catch (e: Exception) {
            throw RuntimeException("Unable to increment attempt number. examCode: ${result.exam!!.code}, " +
                    "userCode: ${result.student!!.code}, code: ${result.code}")
        }
    }
}
