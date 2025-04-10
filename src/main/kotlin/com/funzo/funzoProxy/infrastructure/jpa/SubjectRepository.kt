package com.funzo.funzoProxy.infrastructure.jpa

import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.infrastructure.jpa.projection.ExamCountPerSubjectProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface SubjectRepository: JpaRepository<Subject, Long> {
    fun deleteByCode(code: String)
    fun findByCode(code: String): Subject
    @Query("""
        SELECT subjects.name as subjectName, count(*) as examCount
        FROM subjects
        JOIN exams on exams.subject_code = subjects.code
        Group by subjects.code;
    """ , nativeQuery = true)
    fun findExamCountPerSubject(): List<ExamCountPerSubjectProjection>
}
