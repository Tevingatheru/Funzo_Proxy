package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.controller.response.ExamCountPerSubject
import com.funzo.funzoProxy.application.controller.response.GetAllSubjectStatsResponse
import com.funzo.funzoProxy.application.query.GetAllSubjectStatsQuery
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.dto.AllSubjectStatsDto
import org.springframework.stereotype.Component

@Component
class GetAllSubjectStatsQueryHandler(
    private val subjectService: SubjectService,
    private val userService: UserService
): QueryHandler<GetAllSubjectStatsResponse, GetAllSubjectStatsQuery> {

    override fun handle(query: GetAllSubjectStatsQuery): GetAllSubjectStatsResponse {
        if (userService.userOfTypeExists(
                userCode = query.adminCode,
                userType = UserType.ADMINISTRATOR
            )){
            return mapToGetAllSubjectStatsResponse(dto = subjectService.getAllStats(adminCode = query.adminCode))
        } else {
            throw IllegalArgumentException("User is not an admin. userCode: ${query.adminCode}")
        }
    }

    private fun mapToGetAllSubjectStatsResponse(dto : AllSubjectStatsDto) : GetAllSubjectStatsResponse {
        val examCountPerSubject: MutableList<ExamCountPerSubject> = mutableListOf()
        dto.examCountPerSubject.forEach {
            examCountPerSubject.add(ExamCountPerSubject(
                subjectName = it.first,
                examCount = it.second
            ))
        }
        return GetAllSubjectStatsResponse(
            subjectCount = dto.subjectCount,
            examCountPerSubject = examCountPerSubject
        )
    }
}
