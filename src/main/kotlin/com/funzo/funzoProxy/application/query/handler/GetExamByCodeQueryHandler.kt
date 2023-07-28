package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetExamByCodeQuery
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamResponse
import com.funzo.funzoProxy.domain.exam.ExamService

class GetExamByCodeQueryHandler(private val examService: ExamService): QueryHandler<ExamResponse, GetExamByCodeQuery> {
    override fun handle(query: GetExamByCodeQuery): ExamResponse? {
        val exam = examService.findByCode(query.code)
        return mapToResponse(exam)
    }

    private fun mapToResponse(exam: Exam): ExamResponse {
        return ExamResponse(
            code = exam.code,
            subjectCode = exam.subject!!.code,
            level = exam.level
            )
    }
}
