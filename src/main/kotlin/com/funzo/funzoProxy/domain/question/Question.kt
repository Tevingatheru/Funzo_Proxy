package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.option.Option
import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_code", referencedColumnName = "code", nullable = true)
    val exam: Exam? = null,

    @Column(unique = true, name = "code", nullable = false)
    val code: String? = null,

    @Column(name = "question")
    val question: String? = null,

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "question")
    @JoinColumn(name = "option_code", referencedColumnName = "code")
    val option: Option? = null,

    @Column(name = "image")
    val image: String? = null
) {

}
