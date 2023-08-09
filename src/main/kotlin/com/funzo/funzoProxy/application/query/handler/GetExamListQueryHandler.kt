package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetExamListQuery
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto
import org.springframework.stereotype.Component

@Component
class GetExamListQueryHandler(private val examService: ExamService)
    : QueryHandler<ExamListDto, GetExamListQuery> {
    override fun handle(query: GetExamListQuery): ExamListDto {
        return mapToResponse(examService.findAll())
    }

    private fun mapToResponse(examList: List<Exam>): ExamListDto {
        val examListDto = ExamListDto()
        for (exam: Exam in examList) {
            examListDto.add(exam)
        }

        return examListDto
    }
}
