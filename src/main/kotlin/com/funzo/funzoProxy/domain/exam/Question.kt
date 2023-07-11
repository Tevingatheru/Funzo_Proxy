package com.funzo.funzoProxy.domain.exam

import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "exam_code")
    val exam: Exam,

    @Column(unique = true, name = "code")
    val code: String?,

    @Column(name = "question")
    val question: String,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "question_type_code")
    val type: QuestionType,

    @Column(name = "image")
    val image: String
) {
    constructor(
        exam: Exam,
        question: String,
        type: QuestionType,
        image: String
    ) : this(0, exam, null, question, type, image)
}
