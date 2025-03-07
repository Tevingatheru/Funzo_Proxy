package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.ExamMapper
import com.funzo.funzoProxy.application.query.GetExamListQuery
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto
import org.springframework.stereotype.Component

@Component
class GetExamListQueryHandler(private val examService: ExamService)
    : QueryHandler<ExamListDto, GetExamListQuery> {
    override fun handle(query: GetExamListQuery): ExamListDto {
        return ExamMapper.mapToExamListResponse(examService.findAll())
    }
}
