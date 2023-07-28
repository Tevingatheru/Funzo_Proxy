package com.funzo.funzoProxy.domain.subject

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor

@Entity
@Table(name = "subjects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, name = "code")
    val code: String? = null,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "category")
    var category: String? = null
)
