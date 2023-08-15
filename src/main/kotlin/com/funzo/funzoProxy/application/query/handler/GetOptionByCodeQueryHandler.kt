package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.OptionMapper
import com.funzo.funzoProxy.application.query.GetOptionByCodeQuery
import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import org.springframework.stereotype.Component

@Component
class GetOptionByCodeQueryHandler(
    private val optionService: OptionService
): QueryHandler<OptionDto, GetOptionByCodeQuery> {
    override fun handle(query: GetOptionByCodeQuery): OptionDto {
        return OptionMapper.mapToOptionDto(optionService.getByCode(code = query.code))
    }
}
