package com.funzo.funzoProxy.domain.result

import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.exam.Exam
import jakarta.persistence.*


@Entity
@Table(name = "results")
data class Result(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @ManyToOne
    @JoinColumn(name = "exam_code")
    val exam: Exam,

    @ManyToOne
    @JoinColumn(name = "student_code")
    val student: User,

    @Column(unique = true)
    val code: String,

    val score: String,

    val attempts: Int
)
