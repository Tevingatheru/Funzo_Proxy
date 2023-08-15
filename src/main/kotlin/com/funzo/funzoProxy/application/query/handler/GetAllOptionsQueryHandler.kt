package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.application.query.GetAllOptionsQuery
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionListDto
import org.springframework.stereotype.Component

@Component
class GetAllOptionsQueryHandler(
    private val optionService: OptionService
): QueryHandler<OptionListDto, GetAllOptionsQuery> {
    override fun handle(query: GetAllOptionsQuery): OptionListDto {
        return OptionMapper.mapToOptionListDto(optionService.findAll())
    }
}
