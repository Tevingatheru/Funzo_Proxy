package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetAllUserCountQuery
import com.funzo.funzoProxy.domain.user.UserService
import com.funzo.funzoProxy.domain.user.UserType
import com.funzo.funzoProxy.infrastructure.dao.UserCountDao
import com.funzo.funzoProxy.infrastructure.dto.GetAllUserCountDto
import com.funzo.funzoProxy.infrastructure.util.LogLevel
import com.funzo.funzoProxy.infrastructure.util.LoggerUtils
import org.springframework.stereotype.Component
import kotlin.coroutines.coroutineContext

@Component
class GetAllUserCountQueryHandler(
    private val userService: UserService
): QueryHandler<GetAllUserCountDto, GetAllUserCountQuery>  {

    override fun handle(query: GetAllUserCountQuery): GetAllUserCountDto {

        val total = userService.getTotalUserCount()

        val adminSum: Int = userService.getTotalAdminCount()
        val teacherSum: Int = userService.getTotalTeacherCount()
        val studentSum: Int = userService.getTotalStudentCount()

        return GetAllUserCountDto(totalCount = total, adminCount = adminSum , teacherCount = teacherSum, studentCount = studentSum)
    }
}
