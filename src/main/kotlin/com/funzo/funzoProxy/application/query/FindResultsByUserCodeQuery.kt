package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QuerylResultListDto

class FindResultsByUserCodeQuery(val userCode: String)
    : Query<QuerylResultListDto> {

}
