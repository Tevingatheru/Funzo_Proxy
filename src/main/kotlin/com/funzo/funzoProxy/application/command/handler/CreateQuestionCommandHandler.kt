package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.command.CreateQuestionCommand
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamRepository
import com.funzo.funzoProxy.domain.exam.Question
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class CreateQuestionCommandHandler(private val examRepository: ExamRepository) {

    @Transactional
    fun handle(command: CreateQuestionCommand) {
        val exam: Exam = examRepository.findByCode(command.examCode!!)
            ?: throw IllegalArgumentException("Exam not found for code: ${command.examCode}")

        val question = Question( exam, command.questionText, command.questionType, command.image)
        exam.addQuestion(question)

        examRepository.save(exam)
    }
}
