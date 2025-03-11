package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.mapper.ExamMapper
import com.funzo.funzoProxy.application.query.GetTeachersExamListQuery
import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.exam.ExamService
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.infrastructure.dto.TeachersExamListDto
import org.springframework.stereotype.Component

@Component
class GetTeachersExamListQueryHandler(private val userServiceImpl: UserService, private val examServiceImpl: ExamService)
    : QueryHandler<TeachersExamListDto, GetTeachersExamListQuery> {
    override fun handle(query: GetTeachersExamListQuery): TeachersExamListDto {
        val user: User = userServiceImpl.findTeacherByCode(code = query.userCode)

        val examList: List<Exam> = examServiceImpl.findTeachersExamList(teacherCode = user.code!!)

        return ExamMapper.mapToExamListResponseToTeachersExamListDto(examList)
    }
}
