package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.infrastructure.dto.AddQuestionDto
import com.funzo.funzoProxy.infrastructure.dto.EditQuestionDto
import com.funzo.funzoProxy.infrastructure.dto.ExamQuestionsDto
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.infrastructure.dto.QuestionDto
import com.funzo.funzoProxy.infrastructure.dto.QuestionListDto

object QuestionDtoMapper {
    fun mapToQuestionsByExamCodeResponse(questions: MutableList<Question>): ExamQuestionsDto {
        return ExamQuestionsDto(questions)
    }

    fun mapToAddQuestionCommandResponse(question: Question): AddQuestionDto {
        return AddQuestionDto(code = question.code)
    }

    fun mapToQuestionDto(question: Question): QuestionDto {
        return QuestionDto(
            code = question.code,
            text = question.question,
            questionType = question.type.toString(),
            examCode = question.exam!!.code
        )
    }

    fun mapToQuestionListDto(allQuestions: List<Question>): QuestionListDto {
        val questionListDto : QuestionListDto = QuestionListDto()
        allQuestions.forEach {
            questionListDto.add(QuestionDto(
                examCode = it.exam!!.code,
                questionType = it.type.toString(),
                text = it.question,
                code = it.code
            ))
        }
        return questionListDto
    }

    fun mapToEditQuestionResponse(question: Question): EditQuestionDto {
        return EditQuestionDto(question.code, question.image, question.question)
    }
}
