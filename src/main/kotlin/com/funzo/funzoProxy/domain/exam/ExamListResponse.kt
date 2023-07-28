package com.funzo.funzoProxy.domain.exam

class ExamListResponse(private val subjectCode: String) {
    val examDtoList: List<ExamDto> = ArrayList()
    fun add(exam: Exam) {
        val examDto = ExamDto(
            exam.code,
            exam.level,
        )
        examDtoList.plus(examDto)
    }
}

class ExamDto(code: String?, level: Int?) {

}
