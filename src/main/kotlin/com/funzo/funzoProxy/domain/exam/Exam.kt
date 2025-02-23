package com.funzo.funzoProxy.domain.exam

import com.funzo.funzoProxy.domain.question.Question
import com.funzo.funzoProxy.domain.subject.Subject
import com.funzo.funzoProxy.domain.user.User
import jakarta.persistence.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

@Entity
@Table(name = "exams")
data class Exam(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code", referencedColumnName = "code")
    var subject: Subject? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_code", referencedColumnName = "code", nullable = false)
    val userCode: User,

    @Column(unique = true, name = "code", nullable = false)
    val code: String? = null,

    @OneToMany(mappedBy = "exam", cascade = [CascadeType.ALL],
        orphanRemoval = true, fetch = FetchType.LAZY)
    val questions: MutableList<Question>? = mutableListOf(),

    @Column(name= "description", nullable = false)
    val description: String
) {
    constructor(code: String, subject: Subject, userCode: User, description: String)
            : this(id = null, subject = subject,code = code, userCode = userCode, description = description)

}
