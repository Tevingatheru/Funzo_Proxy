package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteSubjectCommand
import com.funzo.funzoProxy.domain.subject.SubjectService
import org.springframework.stereotype.Component

@Component
class DeleteSubjectCommandHandler(private val subjectService: SubjectService): CommandHandler<Unit, DeleteSubjectCommand> {
    override fun handle(command: DeleteSubjectCommand) {
        subjectService.deleteSubjectByCode(command.code)
    }
}