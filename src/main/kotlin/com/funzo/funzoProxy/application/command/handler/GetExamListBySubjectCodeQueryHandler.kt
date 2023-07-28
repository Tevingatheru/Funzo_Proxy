package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.query.GetExamListBySubjectCodeQuery
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamListResponse
import com.funzo.funzoProxy.domain.exam.ExamService

class GetExamListBySubjectCodeQueryHandler(private val examService: ExamService)
    : QueryHandler<ExamListResponse, GetExamListBySubjectCodeQuery>
{
    override fun handle(query: GetExamListBySubjectCodeQuery): ExamListResponse? {
        return mapToExamListResponse(examService.findExamsBySubjectCode(query.subjectCode), query.subjectCode)
    }

    private fun mapToExamListResponse(examList: List<Exam>, subjectCode: String): ExamListResponse? {
        val examListResponse = ExamListResponse(subjectCode)
        for (exam: Exam in examList) {
            examListResponse.add(exam)
        }

        return examListResponse
    }
}