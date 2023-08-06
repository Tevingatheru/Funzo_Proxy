package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QuerylResultListDto

class FetchResultsByExamCodeAndUserCodeQuery(
    val examCode: String,
    val userCode: String): Query<QuerylResultListDto>
{

}
