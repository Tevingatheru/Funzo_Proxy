package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.domain.question.QuestionService
import org.springframework.stereotype.Component

@Component
class AddQuestionCommandHandler(
    private val questionService: QuestionService
)
    : CommandHandler<AddQuestionCommandResponse, AddQuestionCommand>
{
    override fun handle(command: AddQuestionCommand): AddQuestionCommandResponse {
        return QuestionDtoMapper.mapToAddQuestionCommandResponse(
            questionService.addQuestion(
                examCode = command.examCode,
                questionText = command.questionText,
                image = command.image)
        )
    }
}
