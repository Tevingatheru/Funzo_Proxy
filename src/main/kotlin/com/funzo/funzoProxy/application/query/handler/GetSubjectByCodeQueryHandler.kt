package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetSubjectByCodeQuery
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.infrastructure.dto.GetSubjectByCodeQueryDto
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository
import org.springframework.stereotype.Component

@Component
class GetSubjectByCodeQueryHandler (private val subjectService: SubjectService)
    : QueryHandler<GetSubjectByCodeQueryDto, GetSubjectByCodeQuery> {
    override fun handle(query: GetSubjectByCodeQuery): GetSubjectByCodeQueryDto {
        val subject = subjectService.findByCode(query.code)
        return mapToDto(subject)
    }

    private fun mapToDto(subject: Subject): GetSubjectByCodeQueryDto {
        return GetSubjectByCodeQueryDto(category = subject.category, code = subject.code, description = subject.description, name = subject.name)
    }
}