package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.GetSubjectByCodeQueryDto

class GetSubjectByCodeQuery(val code: String) : Query<GetSubjectByCodeQueryDto> {

}
