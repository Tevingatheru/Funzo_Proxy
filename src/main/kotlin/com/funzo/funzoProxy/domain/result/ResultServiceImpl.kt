package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.ResultRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
                student = userRepository.findStudentByUserCode(userCode) ?: throw NotFoundException(),
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                score = score
            )

            val resultList = resultRepository.findByExamCodeAndUserCode(examCode, userCode)

            if (resultList.isNotEmpty()) {
                attemptNoIncrement(resultList, result)
            }
            return resultRepository.saveAndFlush(result)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findByCode(code: String): Result {
        return try {
            resultRepository.findByCode(code = code)
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
        try{
            resultRepository.deleteByCode(code = code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to delete result with code: $code", e)
        }
    }

    private fun attemptNoIncrement(
        resultList: List<Result>,
        result: Result
    ) {
        try {
            result.attemptNo = resultList.sortedBy(Result::attemptNo)
                .get(0)
                .incrementAttemptNo()
        } catch (e: Exception) {
            throw RuntimeException("Unable to increment attempt number. examCode: ${result.exam!!.code}, " +
                    "userCode: ${result.student!!.code}, code: ${result.code}")
        }
    }
}
