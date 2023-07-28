package com.funzo.funzoProxy.domain.subject

import jakarta.persistence.*


@Entity
@Table(name = "subjects")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Column(unique = true, name = "code")
    val code: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "category")
    var category: String
)
