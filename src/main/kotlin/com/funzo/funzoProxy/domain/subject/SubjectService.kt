package com.funzo.funzoProxy.domain.subject

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.infrastructure.dto.AllSubjectStatsDto

interface SubjectService {
    fun createSubject(createSubjectCommand: CreateSubjectCommand): Subject
    fun updateSubjectDetails(code: String, category: String, description: String, name: String): Subject
    fun deleteSubjectByCode(code: String)
    fun findByCode(code: String): Subject
    fun findAllSubjects(): List<Subject>
    fun getAllStats(adminCode: String): AllSubjectStatsDto
}
