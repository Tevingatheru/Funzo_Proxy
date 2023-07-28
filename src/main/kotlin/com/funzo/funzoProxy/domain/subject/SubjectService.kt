package com.funzo.funzoProxy.domain.subject

import com.funzo.funzoProxy.application.command.CreateSubjectCommand

interface SubjectService {
    fun createSubject(createSubjectCommand: CreateSubjectCommand): Subject
    fun updateSubjectDetails(code: String, category: String, description: String, name: String): Subject
    fun deleteSubjectByCode(code: String)
    fun findByCode(code: String): Subject
}