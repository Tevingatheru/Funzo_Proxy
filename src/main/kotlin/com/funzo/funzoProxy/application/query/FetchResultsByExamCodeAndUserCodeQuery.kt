package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QueryResultListDto

class FetchResultsByExamCodeAndUserCodeQuery(
    val examCode: String,
    val userCode: String): Query<QueryResultListDto>
{

}
