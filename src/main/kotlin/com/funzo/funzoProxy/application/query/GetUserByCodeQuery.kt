package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.GetUserDto

class GetUserByCodeQuery(val code: String) : Query<GetUserDto> {

}
