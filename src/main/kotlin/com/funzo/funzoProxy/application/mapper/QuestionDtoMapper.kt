package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.application.controller.response.AddQuestionCommandResponse
import com.funzo.funzoProxy.application.controller.response.QuestionsByExamCodeResponse
import com.funzo.funzoProxy.domain.question.AddQuestionResponse
import com.funzo.funzoProxy.domain.question.EditQuestionResponse
import com.funzo.funzoProxy.domain.question.ExamQuestionsResponse
import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.infrastructure.dto.QuestionDto
import com.funzo.funzoProxy.infrastructure.dto.QuestionListDto

object QuestionDtoMapper {
    fun mapToQuestionsByExamCodeResponse(examQuestionsResponse: ExamQuestionsResponse): QuestionsByExamCodeResponse {
        TODO("Not yet implemented")
    }

    fun mapToAddQuestionCommandResponse(addQuestion: AddQuestionResponse): AddQuestionCommandResponse {
        TODO("Not yet implemented")
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

    fun mapToEditQuestionResponse(question: Question): EditQuestionResponse {
        return EditQuestionResponse(question.code, question.image, question.question)
    }
}