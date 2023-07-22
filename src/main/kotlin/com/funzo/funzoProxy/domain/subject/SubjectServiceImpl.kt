package com.funzo.funzoProxy.domain.subject

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.infrastructure.GenerateCodeServiceImpl
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class SubjectServiceImpl() : SubjectService {
    private lateinit var subjectRepository: SubjectRepository
    constructor(subjectRepository: SubjectRepository) : this(){
        this.subjectRepository = subjectRepository
    }

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

    override fun updateSubjectDetails(code: String): Subject {
        try {
            val subject = subjectRepository.findByCode(code)
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
}