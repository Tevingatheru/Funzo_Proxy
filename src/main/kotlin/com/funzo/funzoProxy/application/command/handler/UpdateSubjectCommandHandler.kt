package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.UpdateSubjectCommand
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectService
import org.springframework.stereotype.Component

@Component
class UpdateSubjectCommandHandler (
    private val subjectService: SubjectService): CommandHandler< UpdateSubjectCommand> {
    override fun handle(command: UpdateSubjectCommand) {
        subjectService.updateSubjectDetails(command.code, command.category, command.description, command.name)
    }
}