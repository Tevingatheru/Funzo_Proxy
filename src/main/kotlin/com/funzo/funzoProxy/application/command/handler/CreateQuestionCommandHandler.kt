package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateQuestionCommand
import com.funzo.funzoProxy.application.controller.response.CreateQuestionCommandResponse
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.domain.exam.Question
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CreateQuestionCommandHandler(private val examService: ExamService): CommandHandler<CreateQuestionCommandResponse, CreateQuestionCommand> {

    @Transactional
    override fun handle(command: CreateQuestionCommand): CreateQuestionCommandResponse {
        TODO("Not yet implemented")
    }

    private fun mapToResponse(save: Exam): CreateQuestionCommandResponse {
        TODO("Not yet implemented")
    }
}
