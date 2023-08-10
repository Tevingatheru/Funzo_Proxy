package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.option.Option
import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_code", referencedColumnName = "code", nullable = true)
    val exam: Exam,

    @Column(unique = true, name = "code")
    val code: String?,

    @Column(name = "question")
    var question: String?,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "question")
    @JoinColumn(name = "option_code", referencedColumnName = "code")
    val type: Option,

    @Column(name = "image")
    var image: String?
) {
    constructor(
        exam: Exam,
        question: String,
        type: Option,
        image: String
    ) : this(0, exam, null, question, type, image)
}
