package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.dto.ExamContentDto
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import com.funzo.funzoProxy.infrastructure.jpa.UserRepository
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
    private val subjectRepository: SubjectRepository,
    private val userRepository: UserRepository,
) : ExamService  {

    override fun findByCode(examCode: String): Exam {
        return examRepository.findByCode(examCode) ?: throw NotFoundException()
    }

    override fun save(subjectCode: String, userCode: String, examDescription: String): Exam {
        try {
            val subject: Subject = findSubjectByCode(subjectCode)
            val user: User = findTeacherByUserCoder(userCode = userCode)

            val exam = Exam(
                code = generateCodeServiceImpl.generateCodeWithLength(7),
                subject = subject,
                user = user,
                description = examDescription
            )
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

    private fun findTeacherByUserCoder(userCode: String): User {
        try {
            return userRepository.findTeacherByUserCode(userCode = userCode)
        } catch (e: Exception) {
            throw RuntimeException("Could not find teacher by code.")
        }
    }

    private fun findSubjectByCode(code: String): Subject {
        try {
            return subjectRepository.findByCode(code)
        } catch (e: Exception) {
            throw RuntimeException("Could not find subject by code")
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

    override fun findTeachersExamList(teacherCode: String): List<Exam> {
        try {
            LoggerUtils.log(
                level = LogLevel.INFO,
                message = "Fetching all exam owned by given teacher.",
                className = this::class.java,
                diagnosisMap = mapOf(Pair("userCode", teacherCode))
            )
            return examRepository.findByUserCode(userCode = teacherCode)
        } catch (e: Exception) {
            throw RuntimeException(e.localizedMessage)
        }
    }

    override fun getExamContentByCode(examCode: String, optionServiceImpl: OptionService): ExamContentDto {
        val exam :Exam= this.findByCode(examCode = examCode)
        val examQuestions = exam.questions!!

        val examContentDto = ExamContentDto()
        examContentDto.setExamCode(examCode = exam.code!!)
        examContentDto.setTotalNumberOfQuestions(examQuestions = examQuestions)
        examContentDto.setQuestionsAndOptions(
            examQuestions = examQuestions,
            optionServiceImpl = optionServiceImpl,
        )

        LoggerUtils.log(
            level = LogLevel.INFO,
            message = "output of getExamContentByCode.",
            className = this::class.java,
            diagnosisMap = mapOf(Pair("examContentDto", examContentDto),)
        )

        return examContentDto
    }
}
