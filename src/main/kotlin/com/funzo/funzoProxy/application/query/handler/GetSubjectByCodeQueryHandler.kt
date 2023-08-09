package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetSubjectByCodeQuery
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.infrastructure.dto.SubjectDto
import org.springframework.stereotype.Component

@Component
class GetSubjectByCodeQueryHandler (private val subjectService: SubjectService)
    : QueryHandler<SubjectDto, GetSubjectByCodeQuery> {
    override fun handle(query: GetSubjectByCodeQuery): SubjectDto {
        val subject = subjectService.findByCode(query.code)
        return mapToDto(subject)
    }

    private fun mapToDto(subject: Subject): SubjectDto {
        return SubjectDto(
            category = subject.category!!,
            code = subject.code!!,
            description = subject.description!!,
            name = subject.name!!)
    }
}
