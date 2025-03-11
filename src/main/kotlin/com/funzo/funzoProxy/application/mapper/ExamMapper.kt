package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto
import com.funzo.funzoProxy.infrastructure.dto.TeachersExamListDto

object ExamMapper {
    fun mapToExamListResponse(examList: List<Exam>): ExamListDto {
        val examListDto = ExamListDto()

        for (exam: Exam in examList) {
            examListDto.add(exam)
        }

        return examListDto
    }

    fun mapToExamListResponseToTeachersExamListDto(examList: List<Exam>): TeachersExamListDto {
        val teachersExamListDto: TeachersExamListDto = TeachersExamListDto()

        for (exam: Exam in examList) {
            teachersExamListDto.add(exam)
        }

        return  teachersExamListDto
    }
}
