package com.funzo.funzoProxy.domain.subject

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.LogLevel
import com.funzo.funzoProxy.infrastructure.LoggerUtils
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
@Transactional()
class SubjectServiceImpl(
    @Autowired private val subjectRepository: SubjectRepository
) : SubjectService {

    override fun createSubject(createSubjectCommand: CreateSubjectCommand): Subject {
        try {
            val subject = Subject(
                null,
                GenerateCodeServiceImpl().generateCodeWithLength(7),
                createSubjectCommand.name,
                createSubjectCommand.description,
                createSubjectCommand.category)
            return subjectRepository.save(subject)
        } catch (e: Exception) {
            throw RuntimeException("Unable to save subject: $createSubjectCommand")
        }
    }

    override fun updateSubjectDetails(code: String, category: String, description: String, name: String): Subject {
        try {
            val subject = subjectRepository.findByCode(code)
            if (category.isNotEmpty()) {
                subject.category = category
            }
            if (description.isNotEmpty()) {
                subject.description = description
            }
            if (name.isNotEmpty()) {
                subject.name = name
            }
            return subjectRepository.save(subject)
        } catch (e: Exception) {
            throw RuntimeException("Unable to update subject with code: $code")
        }
    }

    override fun deleteSubjectByCode(code: String) {
        try {
            subjectRepository.deleteByCode(code)
        } catch (e: Exception) {
            throw RuntimeException("Unable to delete subject by code: $code")
        }
    }

    override fun findByCode(code: String): Subject {
        try {
            return subjectRepository.findByCode(code)
        } catch (e: EmptyResultDataAccessException) {
            throw NotFoundException()
        } catch (e: Exception) {
            LoggerUtils.log(LogLevel.ERROR, "Unable to find subject by code: $code", mapOf(Pair("error", e)),this::class.java)
            throw RuntimeException(e)
        }
    }
}