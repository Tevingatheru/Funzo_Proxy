package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.application.command.AddQuestionCommand
import com.funzo.funzoProxy.application.command.CreateExamCommand
import com.funzo.funzoProxy.infrastructure.jpa.ExamRepository
import org.springframework.stereotype.Service

@Service
class ExamServiceImpl(private val examRepository: ExamRepository) : ExamService  {

    override fun findByCode(examCode: String): Exam? {
        return examRepository.findByCode(examCode)
    }

    override fun save(createExamCommand: CreateExamCommand): Exam {
        val exam = Exam(createExamCommand.level)
        if (createExamCommand.questions != null) {
            createExamCommand.questions.forEach { questionCommand ->
                val question =
                    Question(exam, questionCommand.questionText, questionCommand.questionType, questionCommand.image!!)
                exam.addQuestion(question)
            }
        }
        examRepository.save(exam)
        return exam
    }

    override fun delete(examCode: String) {
        examRepository.deleteByCode(examCode)
    }

    override fun addQuestions(command: AddQuestionCommand): Exam {
        TODO("Not yet implemented")
    }
}
