package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.DeleteExamCommand
import com.funzo.funzoProxy.domain.exam.DeleteExamCommandDto
import com.funzo.funzoProxy.domain.exam.ExamService

class DeleteExamCommandHandler(private val examService: ExamService): CommandHandler<DeleteExamCommandDto, DeleteExamCommand> {
    override fun handle(command: DeleteExamCommand): DeleteExamCommandDto {
        return mapToDto(examService.deleteByCode(command.code))
    }

    private fun mapToDto(deleteByCode: Unit): DeleteExamCommandDto {
        return DeleteExamCommandDto()
    }
}