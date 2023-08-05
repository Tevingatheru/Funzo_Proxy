package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.ResultRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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
    override fun createResult(examCode: String, userCode: String, score: String): Result {
        try {
            val exam: Exam = examRepository.findByCode(examCode = examCode)!!
            val studentUser: User = userRepository.findStudentByUserCode(userCode)
            val result = Result(
                exam = exam,
                student = studentUser,
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                score = score
            )
            return resultRepository.save(result)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findByCode(code: String): Result {
        return try {
            resultRepository.findByCode(code = code)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findAll(): List<Result> {
        return try{
            resultRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findResultsByUserCode(userCode: String): List<Result> {
        return try {
            resultRepository.findByUserCode(userCode = userCode)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun findResultsByExamCodeAndUserCode(examCode: String, userCode: String): List<Result> {
        return try {
            resultRepository.findByEmailCodeAndUserCode(userCode = userCode, emailCode = examCode)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun deleteByCode(code: String) {
        resultRepository.deleteByCode(code = code)
    }
}
