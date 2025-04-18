package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.exam.Exam
import jakarta.persistence.*

@Entity
@Table(name = "results")
data class Result(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_code", referencedColumnName = "code")
    var exam: Exam? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_code", referencedColumnName = "code")
    var student: User? = null,

    @Column(unique = true)
    val code: String? = null,

    @Column(nullable = false)
    val score: Double? = null,

    var attemptNo: Int = 0
){
    constructor(exam: Exam, student: User, code: String, score: Double) : this(id = null,
        exam = exam,
        student = student,
        code = code,
        score = score,
        )

    fun incrementAttemptNo(): Int {
        return ++this.attemptNo
    }
}
