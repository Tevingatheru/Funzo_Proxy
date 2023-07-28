package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service

@Service
class ExamServiceImpl(private val examRepository: ExamRepository) : ExamService  {

    override fun findByCode(examCode: String): Exam {
        return examRepository.findByCode(examCode) ?: throw ChangeSetPersister.NotFoundException()
    }

    override fun save(createExamCommand: CreateExamCommand): Exam {
        val exam = Exam(createExamCommand.level)

        examRepository.save(exam)
        return exam
    }

    override fun deleteByCode(examCode: String) {
        examRepository.deleteByCode(examCode)
    }

    override fun findExamsBySubjectCode(subjectCode: String): List<Exam> {
        return examRepository.findBySubjectCode(subjectCode)
    }
}
