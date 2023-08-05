package com.funzo.funzoProxy.domain.question

import com.funzo.funzoProxy.domain.exam.Exam
import jakarta.persistence.*

@Entity
@Table(name = "questions")
data class Question(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "exam_code")
    val exam: Exam,

    @Column(unique = true, name = "code")
    val code: String?,

    @Column(name = "question")
    var question: String?,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinColumn(name = "question_type_code")
    var type: QuestionType?,

    @Column(name = "image")
    var image: String?
) {
    constructor(
        exam: Exam,
        question: String,
        type: QuestionType?,
        image: String?
    ) : this(0, exam, null, question, type, image)

//    constructor(): this(id = null, exam = Exam(), code = null, question = null, type = null, image = null)

}
