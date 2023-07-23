package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.command.bus.Command
import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.domain.exam.Question
import jakarta.transaction.Transactional
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service

@Service
@Transactional
@NoArgsConstructor
class CreateExamCommandHandler(private val examService: ExamService): CommandHandler<CreateExamCommandResponse, CreateExamCommand> {

    override fun handle(command: CreateExamCommand): CreateExamCommandResponse {
        val exam = Exam(command.level)
        command.questions.forEach { questionCommand ->
            val question = Question(exam, questionCommand.questionText, questionCommand.questionType, questionCommand.image)
            exam.addQuestion(question)
        }
        return mapToResponse(examService.save(exam))
    }

    private fun mapToResponse(save: Exam): CreateExamCommandResponse {
        TODO("Not yet implemented")
    }

}
