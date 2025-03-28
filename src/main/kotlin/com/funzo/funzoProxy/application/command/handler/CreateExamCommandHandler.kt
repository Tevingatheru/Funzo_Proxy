package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.application.controller.response.CreateExamCommandResponse
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import jakarta.transaction.Transactional
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service

@Service
@Transactional
@NoArgsConstructor
class CreateExamCommandHandler(private val examService: ExamService)
    : CommandHandler<CreateExamCommandResponse, CreateExamCommand> {

    override fun handle(command: CreateExamCommand): CreateExamCommandResponse {
        return mapToResponse(
            examService.save(
                subjectCode = command.subjectCode, userCode = command.userCode, examDescription = command.examDescription
            )
        )
    }

    private fun mapToResponse(exam: Exam): CreateExamCommandResponse {
        return CreateExamCommandResponse(
            code = exam.code!!,
            userCode = exam.user!!.code!!,
            subject = exam.subject!!.name!!,
            description = exam.description!!
        )
    }
}
