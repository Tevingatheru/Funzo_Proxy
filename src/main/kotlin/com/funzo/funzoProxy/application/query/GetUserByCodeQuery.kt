package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.GetUserByCodeDto

class GetUserByCodeQuery(val code: String) : Query<GetUserByCodeDto> {

}
