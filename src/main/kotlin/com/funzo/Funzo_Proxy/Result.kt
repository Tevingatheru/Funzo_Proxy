package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan


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
