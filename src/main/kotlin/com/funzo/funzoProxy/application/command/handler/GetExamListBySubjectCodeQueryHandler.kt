package com.funzo.funzoProxy.application.command.handler

import com.funzo.funzoProxy.application.mapper.ExamMapper
import com.funzo.funzoProxy.application.query.GetExamListBySubjectCodeQuery
import com.funzo.funzoProxy.application.query.handler.QueryHandler
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto
import com.funzo.funzoProxy.domain.exam.ExamService
import org.springframework.stereotype.Component

@Component
class GetExamListBySubjectCodeQueryHandler(private val examService: ExamService)
    : QueryHandler<ExamListDto, GetExamListBySubjectCodeQuery>
{
    override fun handle(query: GetExamListBySubjectCodeQuery): ExamListDto {
        return ExamMapper.mapToExamListResponse(examService.findExamsBySubjectCode(query.subjectCode))
    }
}
