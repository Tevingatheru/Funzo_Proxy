package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.UpdateSubjectCommand
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.infrastructure.dto.UpdateSubjectDto
import org.springframework.stereotype.Component

@Component
class UpdateSubjectCommandHandler (
    private val subjectService: SubjectService): CommandHandler<UpdateSubjectDto, UpdateSubjectCommand> {
    override fun handle(command: UpdateSubjectCommand): UpdateSubjectDto {
        return mapToDto(subjectService.updateSubjectDetails(command.code, command.category, command.description, command.name))
    }

    private fun mapToDto(subject: Subject): UpdateSubjectDto {
        return UpdateSubjectDto(
            category = subject.category,
            code = subject.code,
            description = subject.description,
            name = subject.name)
    }
}