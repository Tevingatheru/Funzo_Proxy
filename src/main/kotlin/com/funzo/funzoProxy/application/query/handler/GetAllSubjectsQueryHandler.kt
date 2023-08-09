package com.funzo.funzoProxy.application.query.handler

import com.funzo.funzoProxy.application.query.GetAllSubjectsQuery
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.subject.SubjectService
import com.funzo.funzoProxy.infrastructure.dto.SubjectListDto
import org.springframework.stereotype.Component

@Component
class GetAllSubjectsQueryHandler(private val subjectService: SubjectService): QueryHandler<SubjectListDto, GetAllSubjectsQuery> {
    override fun handle(query: GetAllSubjectsQuery): SubjectListDto {
        return mapToDto(subjectService.findAllSubjects())
    }

    private fun mapToDto(subjectList: List<Subject>): SubjectListDto {
        val subjectDto = SubjectListDto()
        for(subject in subjectList) {
            subjectDto.add(subject)
        }
        return subjectDto
    }
}