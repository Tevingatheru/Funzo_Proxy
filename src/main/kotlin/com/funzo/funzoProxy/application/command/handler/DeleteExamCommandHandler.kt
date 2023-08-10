package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteExamCommand
import com.funzo.funzoProxy.domain.exam.ExamService
import org.springframework.stereotype.Component

@Component
class DeleteExamCommandHandler(private val examService: ExamService): CommandHandler<Unit, DeleteExamCommand> {
    override fun handle(command: DeleteExamCommand) {
        examService.deleteByCode(command.code)
    }
}
