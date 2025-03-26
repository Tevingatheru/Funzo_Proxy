package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetExamContentQuery
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.ExamContentDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GetExamContentByCodeHandler(
    private val examService: ExamService,
    @Autowired private val optionService: OptionService,
): QueryHandler<ExamContentDto, GetExamContentQuery> {
    override fun handle(query: GetExamContentQuery): ExamContentDto {
        try {
            return examService.getExamContentByCode(
                examCode = query.examCode,
                optionServiceImpl = optionService
            )
        } catch (e: Exception) {
            throw e
        }
    }
}
