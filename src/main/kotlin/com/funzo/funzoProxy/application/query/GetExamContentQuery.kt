package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.ExamContentDto

data class GetExamContentQuery(val examCode: String): Query<ExamContentDto>
