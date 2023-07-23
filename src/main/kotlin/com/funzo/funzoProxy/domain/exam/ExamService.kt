package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.command.AddQuestionCommand
import org.springframework.stereotype.Service

@Service
interface ExamService {
    fun findByCode(examCode: String): Exam?
    fun save(createExamCommand: CreateExamCommand): Exam
    fun delete(examCode: String)
    fun addQuestions(command: AddQuestionCommand): Exam

}
