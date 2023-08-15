package com.funzo.funzoProxy.infrastructure.dto

import com.funzo.funzoProxy.domain.subject.Subject

class SubjectListDto (val subjects: ArrayList<SubjectDto> = ArrayList()){
    fun add(subject: Subject) {
        val subjectListDto = SubjectDto(
            category = subject.category!!,
            code = subject.code!!,
            description = subject.description!!,
            name = subject.name!!)
        subjects.add(element = subjectListDto)
    }
}
