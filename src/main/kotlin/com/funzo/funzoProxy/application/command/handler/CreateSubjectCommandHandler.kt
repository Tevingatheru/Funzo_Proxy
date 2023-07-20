package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectServiceImpl
import org.springframework.stereotype.Component

@Component
class CreateSubjectCommandHandler(private val subjectServiceImpl: SubjectServiceImpl) {

    fun handle(createSubjectCommand: CreateSubjectCommand): Subject {
        validateCommand(createSubjectCommand)
        return subjectServiceImpl.createSubject(createSubjectCommand)
    }

    private fun validateCommand(createSubjectCommand: CreateSubjectCommand) {
        when {
            createSubjectCommand.category.isEmpty() -> {
                throw IllegalArgumentException("Invalid category")
            }
            createSubjectCommand.name.isEmpty() -> {
                throw IllegalArgumentException("Invalid name")
            }
            createSubjectCommand.description.isEmpty() -> {
                throw IllegalArgumentException("Invalid description")
            }
        }
    }
}
