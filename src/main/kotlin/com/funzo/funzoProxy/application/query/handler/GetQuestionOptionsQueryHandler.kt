package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.application.query.GetQuestionOptionsQuery
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionListDto
import org.springframework.stereotype.Component

@Component
class GetQuestionOptionsQueryHandler(
    private val optionService: OptionService
): QueryHandler<OptionListDto, GetQuestionOptionsQuery> {
    override fun handle(query: GetQuestionOptionsQuery): OptionListDto {
        return OptionMapper.mapToOptionListDto(
            optionService.getByQuestionCode(questionCode = query.questionCode))
    }
}
