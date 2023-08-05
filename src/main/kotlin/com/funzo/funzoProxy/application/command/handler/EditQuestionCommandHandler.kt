package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.EditQuestionCommand
import com.funzo.funzoProxy.domain.question.EditQuestionResponse
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.domain.question.QuestionType
import org.springframework.stereotype.Component

@Component
class EditQuestionCommandHandler(private val questionService: QuestionService)
    : CommandHandler<EditQuestionResponse, EditQuestionCommand>
{

    override fun handle(command: EditQuestionCommand): EditQuestionResponse {
        return questionService.modifyQuestion(command.examCode, command.questionCode, command.question, QuestionType.find(command.questionType), command.image)
    }
}
