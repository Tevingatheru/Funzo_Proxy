package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamRepository
import com.funzo.funzoProxy.domain.exam.Question
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class CreateExamCommandHandler(private val examRepository: ExamRepository) {

    @Transactional
    fun handle(command: CreateExamCommand) {
        val exam = Exam(command.level)
        command.questions.forEach { questionCommand ->
            val question = Question(exam, questionCommand.questionText, questionCommand.questionType, questionCommand.image)
            exam.addQuestion(question)
        }
        examRepository.save(exam)
    }
}