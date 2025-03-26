package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.ExamMapper
import com.funzo.funzoProxy.application.query.GetExamContentQuery
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.infrastructure.dto.ExamContentDto

class GetExamContentByCode(
    private val examService: ExamService
): QueryHandler<ExamContentDto, GetExamContentQuery> {
    override fun handle(query: GetExamContentQuery): ExamContentDto {
        return ExamContentDto(
            ExamMapper.(
                    examService.getExamContentByCode(examCode = query.examCode)
                    )
        )
    }
}
