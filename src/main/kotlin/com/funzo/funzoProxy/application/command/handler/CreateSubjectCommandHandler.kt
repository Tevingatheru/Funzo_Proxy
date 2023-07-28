package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateSubjectCommand
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectServiceImpl
import com.funzo.funzoProxy.infrastructure.dto.CreateSubjectDto
import lombok.NoArgsConstructor
import org.springframework.stereotype.Component

@Component
@NoArgsConstructor
class CreateSubjectCommandHandler(private val subjectServiceImpl: SubjectServiceImpl)
    : CommandHandler<CreateSubjectDto, CreateSubjectCommand> {

    override fun handle(command: CreateSubjectCommand): CreateSubjectDto {
        validateCommand(command)
        return mapToDto(subjectServiceImpl.createSubject(command))
    }

    private fun mapToDto(subject: Subject): CreateSubjectDto {
        return CreateSubjectDto(
            category = subject.category,
            code = subject.code,
            description = subject.description,
            name = subject.name
        )
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
