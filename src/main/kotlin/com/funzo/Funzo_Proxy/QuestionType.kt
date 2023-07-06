package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
open class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    open val id: Int = 0

    @Column(name = "code")
    open val code: String? = null
}
