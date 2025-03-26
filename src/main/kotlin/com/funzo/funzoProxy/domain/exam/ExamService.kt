package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.domain.option.OptionService
import com.funzo.funzoProxy.infrastructure.dto.ExamContentDto
import org.springframework.stereotype.Service

@Service
interface ExamService {
    fun findByCode(examCode: String): Exam

    fun deleteByCode(examCode: String)

    fun findExamsBySubjectCode(subjectCode: String): List<Exam>

    fun findAll(): List<Exam>

    fun save(subjectCode: String, userCode: String, examDescription: String): Exam

    fun findTeachersExamList(teacherCode: String): List<Exam>

    fun getExamContentByCode(examCode: String, optionServiceImpl: OptionService): ExamContentDto
}
