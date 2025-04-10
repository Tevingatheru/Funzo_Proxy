package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.application.query.GetQuestionOptionsQuery
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import org.springframework.stereotype.Component

@Component
class GetQuestionOptionsQueryHandler(
    private val optionService: OptionService
): QueryHandler<OptionDto, GetQuestionOptionsQuery> {
    override fun handle(query: GetQuestionOptionsQuery): OptionDto {
        return OptionMapper
            .mapToOptionDto(option = optionService.getByQuestionCode(questionCode = query.questionCode))
    }
}
