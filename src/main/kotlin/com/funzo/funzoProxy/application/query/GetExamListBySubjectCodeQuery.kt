package com.funzo.funzoProxy.application.query

import com.funzo.funzoProxy.domain.exam.ExamListResponse

class GetExamListBySubjectCodeQuery(val subjectCode: String)
    :Query<ExamListResponse>
{

}
