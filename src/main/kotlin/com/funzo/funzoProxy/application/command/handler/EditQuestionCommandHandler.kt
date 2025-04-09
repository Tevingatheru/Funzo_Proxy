package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.EditQuestionCommand
import com.funzo.funzoProxy.application.mapper.QuestionMapper
import com.funzo.funzoProxy.infrastructure.dto.EditQuestionDto
import com.funzo.funzoProxy.domain.question.QuestionService
import org.springframework.stereotype.Component

@Component
class EditQuestionCommandHandler(private val questionService: QuestionService)
    : CommandHandler<EditQuestionDto, EditQuestionCommand>
{

    override fun handle(command: EditQuestionCommand): EditQuestionDto {
        return QuestionMapper.mapToEditQuestionResponse(questionService.modifyQuestion(
            examCode = command.examCode,
            questionCode = command.questionCode,
            questionText = command.question,
            questionImage = command.image
        ))
    }
}
