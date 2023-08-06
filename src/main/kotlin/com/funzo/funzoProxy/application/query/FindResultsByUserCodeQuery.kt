package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QueryResultListDto

class FindResultsByUserCodeQuery(val userCode: String)
    : Query<QueryResultListDto> {

}
