package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.SubjectDto

class GetSubjectByCodeQuery(val code: String) : Query<SubjectDto> {

}
