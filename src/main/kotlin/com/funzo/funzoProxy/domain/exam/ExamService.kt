package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.application.command.CreateExamCommand
import org.springframework.stereotype.Service

@Service
interface ExamService {
    fun findByCode(examCode: String): Exam

    fun save(createExamCommand: CreateExamCommand): Exam

    fun deleteByCode(examCode: String)

    fun findExamsBySubjectCode(subjectCode: String): List<Exam>
}
