package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.RemoveQuestionCommand
import com.funzo.funzoProxy.domain.question.QuestionService
import org.springframework.stereotype.Component

@Component
class RemoveQuestionCommandHandler(
    private val questionService: QuestionService
): CommandHandler<Unit, RemoveQuestionCommand> {
    override fun handle(command: RemoveQuestionCommand) {
        questionService.removeQuestion(questionCode = command.questionCode)
    }
}
