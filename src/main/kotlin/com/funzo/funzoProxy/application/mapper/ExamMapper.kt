package com.funzo.funzoProxy.application.mapper

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.infrastructure.dto.ExamListDto

object ExamMapper {
    fun mapToExamListResponse(examList: List<Exam>): ExamListDto {
        val examListDto = ExamListDto()
        for (exam: Exam in examList) {
            examListDto.add(exam)
        }

        return examListDto
    }
}