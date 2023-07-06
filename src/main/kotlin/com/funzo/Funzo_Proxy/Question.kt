package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan



@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @ManyToOne
    @JoinColumn(name = "exam_code")
    val exam: Exam,

    @Column(unique = true, name = "code")
    val code: String,

    @Column(name = "question")
    val question: String,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "question_type_code")
    val type: QuestionType,

    @Column(name = "image")
    val image: String
)
