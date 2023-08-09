package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.QueryResultDto

class FetchUserByCodeQuery(
    val code: String): Query<QueryResultDto?> {

}
