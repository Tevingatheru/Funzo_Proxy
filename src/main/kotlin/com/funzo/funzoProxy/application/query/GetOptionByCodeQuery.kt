package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.OptionDto

class GetOptionByCodeQuery(val code: String) :
    Query<OptionDto> {

}
