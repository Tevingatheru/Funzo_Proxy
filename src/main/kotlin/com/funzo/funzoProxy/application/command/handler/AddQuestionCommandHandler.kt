package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.mapper.QuestionMapper
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.infrastructure.dto.AddQuestionDto
import org.springframework.stereotype.Component

@Component
class AddQuestionCommandHandler(
    private val questionService: QuestionService
)
    : CommandHandler<AddQuestionDto, AddQuestionCommand>
{
    override fun handle(command: AddQuestionCommand): AddQuestionDto {
        return QuestionMapper.mapToAddQuestionCommandResponse(
            questionService.addQuestion(
                examCode = command.examCode,
                questionText = command.questionText,
                image = command.image)
        )
    }
}
