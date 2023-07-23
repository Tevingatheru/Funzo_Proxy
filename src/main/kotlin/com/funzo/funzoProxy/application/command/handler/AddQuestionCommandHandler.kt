package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AddQuestionCommandHandler(private val examService: ExamService): CommandHandler<AddQuestionCommandResponse, AddQuestionCommand> {

    @Transactional
    override fun handle(command: AddQuestionCommand): AddQuestionCommandResponse {
        return mapToResponse(examService.addQuestions(command))
    }

    private fun mapToResponse(save: Exam): AddQuestionCommandResponse {
        return AddQuestionCommandResponse(save.code!!)
    }
}
