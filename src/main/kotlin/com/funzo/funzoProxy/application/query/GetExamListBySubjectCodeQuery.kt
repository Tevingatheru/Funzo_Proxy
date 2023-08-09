package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.infrastructure.dto.ExamListDto

class GetExamListBySubjectCodeQuery(val subjectCode: String)
    :Query<ExamListDto>
{

}
