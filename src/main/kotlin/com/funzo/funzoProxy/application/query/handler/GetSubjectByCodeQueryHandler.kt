package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetSubjectByCodeQuery
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.infrastructure.dto.GetSubjectByCodeQueryDto
import com.funzo.funzoProxy.infrastructure.jpa.SubjectRepository

class GetSubjectByCodeQueryHandler (private val subjectRepository: SubjectRepository)
    : QueryHandler<GetSubjectByCodeQueryDto, GetSubjectByCodeQuery> {
    override fun handle(query: GetSubjectByCodeQuery): GetSubjectByCodeQueryDto {
        return mapToDto(subjectRepository.findByCode(query.code))
    }

    private fun mapToDto(subject: Subject): GetSubjectByCodeQueryDto {
        return GetSubjectByCodeQueryDto(category = subject.category, code = subject.code, description = subject.description, name = subject.name)
    }
}