package com.funzo.funzoProxy.application.command.handler

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
        return mapToExamListResponse(examService.findExamsBySubjectCode(query.subjectCode))
    }

    private fun mapToExamListResponse(examList: List<Exam>): ExamListDto {
        val examListDto = ExamListDto()
        for (exam: Exam in examList) {
            examListDto.add(exam)
        }

        return examListDto
    }
}
