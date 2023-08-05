package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateQuestionCommand
import com.funzo.funzoProxy.domain.question.AddQuestionResponse
import com.funzo.funzoProxy.domain.question.QuestionService
import com.funzo.funzoProxy.domain.question.QuestionType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CreateQuestionCommandHandler(private val questionService: QuestionService): CommandHandler<AddQuestionResponse, CreateQuestionCommand> {

    @Transactional
    override fun handle(command: CreateQuestionCommand): AddQuestionResponse {
        return questionService.addQuestion(
            examCode = command.examCode!!,
            question =  command.questionText,
            questionType =  QuestionType.find(command.questionType),
            image = command.image,
            correctOption = command.correctOption,
            optionA = command.optionA,
            optionB = command.optionB,
            optionC = command.optionC,
            optionD = command.optionD
        )
    }
}
