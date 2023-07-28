package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.domain.exam.ExamService
import org.springframework.stereotype.Service

@Service
class AddQuestionCommandHandler(private val examService: ExamService): CommandHandler<AddQuestionCommandResponse, AddQuestionCommand> {
    override fun handle(command: AddQuestionCommand): AddQuestionCommandResponse {
        TODO("Not yet implemented")
    }
}
