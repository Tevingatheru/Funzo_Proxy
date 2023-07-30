package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional
class ExamServiceImpl(
    private val examRepository: ExamRepository,
    private val generateCodeServiceImpl: GenerateCodeServiceImpl,
    private val subjectRepository: SubjectRepository
    ) : ExamService  {

    override fun findByCode(examCode: String): Exam {
        return examRepository.findByCode(examCode) ?: throw NotFoundException()
    }

    override fun save(level: Int, subjectCode: String): Exam {
        try {
            val exam = Exam(
                level = level,
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                subject = subjectRepository.findByCode(subjectCode) ?: throw NotFoundException())
            return examRepository.saveAndFlush(exam)
        } catch (e: NotFoundException) {
            throw NotFoundException()
        } catch (e: InvalidDataAccessApiUsageException) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage!!, mapOf(Pair("subjectCode", subjectCode)), this::class.java)
            throw RuntimeException("Transient reference Subject not found. Code: $subjectCode")
        }  catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage!!, this::class.java)
            throw RuntimeException(e)
        }
    }

    override fun deleteByCode(examCode: String) {
        try {
            examRepository.deleteByCode(examCode)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage!!, this::class.java)
            throw RuntimeException(e.localizedMessage)
        }
    }

    override fun findExamsBySubjectCode(subjectCode: String): List<Exam> {
        try {
            return examRepository.findBySubjectCode(subjectCode)
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, e.localizedMessage!!, this::class.java)
            throw RuntimeException(e.localizedMessage)
        }
    }

    override fun findAll(): List<Exam> {
        try {
            return examRepository.findAll()
        } catch (e: Exception) {
            throw RuntimeException(e.localizedMessage)
        }
    }
}
