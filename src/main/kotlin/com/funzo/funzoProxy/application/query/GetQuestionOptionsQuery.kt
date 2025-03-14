package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.OptionDto
import com.funzo.funzoProxy.infrastructure.dto.OptionListDto

class GetQuestionOptionsQuery(val questionCode: String) :
    Query<OptionDto> {

}
