package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.exam.Exam
import jakarta.persistence.*

@Entity
@Table(name = "results")
data class Result(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "exam_code", referencedColumnName = "code", nullable = false)
    val exam: Exam,

    @ManyToOne
    @JoinColumn(name = "student_code", referencedColumnName = "code", nullable = false)
    val student: User,

    @Column(unique = true)
    val code: String,

    @Column(nullable = false)
    val score: Double,

    var attemptNo: Int = 0
){
    constructor(exam: Exam, student: User, code: String, score: Double) : this(id = null,
        exam = exam,
        student = student,
        code = code,
        score = score,
        )

    fun incrementsAttemptNo(): Int {
        return this.attemptNo++
    }
}
