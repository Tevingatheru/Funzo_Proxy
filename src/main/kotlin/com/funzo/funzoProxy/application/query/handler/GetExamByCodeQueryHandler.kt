package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetExamByCodeQuery
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.dto.ExamDto
import com.funzo.funzoProxy.domain.exam.ExamService
import org.springframework.stereotype.Component

@Component
class GetExamByCodeQueryHandler(private val examService: ExamService): QueryHandler<ExamDto, GetExamByCodeQuery> {
    override fun handle(query: GetExamByCodeQuery): ExamDto {
        val exam = examService.findByCode(query.code)
        return mapToResponse(exam)
    }

    private fun mapToResponse(exam: Exam): ExamDto {
        return ExamDto(
            examCode = exam.code,
            subject = exam.subject!!.name,

            )
    }
}
