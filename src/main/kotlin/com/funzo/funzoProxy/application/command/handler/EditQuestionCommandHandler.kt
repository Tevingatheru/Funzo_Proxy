package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.EditQuestionCommand
import com.funzo.funzoProxy.application.mapper.QuestionDtoMapper
import com.funzo.funzoProxy.domain.question.EditQuestionResponse
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.domain.question.QuestionService
import org.springframework.stereotype.Component

@Component
class EditQuestionCommandHandler(private val questionService: QuestionService)
    : CommandHandler<EditQuestionResponse, EditQuestionCommand>
{

    override fun handle(command: EditQuestionCommand): EditQuestionResponse {
        return QuestionDtoMapper.mapToEditQuestionResponse(questionService.modifyQuestion(
            examCode = command.examCode,
            questionCode = command.questionCode,
            questionText = command.question,
            questionImage = command.image
        ))
    }
}
