package com.funzo.funzoProxy.application.command

import com.funzo.funzoProxy.application.query.Query
import com.funzo.funzoProxy.infrastructure.dto.GetUserDto

class GetUserByEmailQuery(val email: String) : Query<GetUserDto> {

}
