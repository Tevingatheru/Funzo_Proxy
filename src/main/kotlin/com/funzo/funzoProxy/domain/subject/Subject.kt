package com.funzo.funzoProxy.domain.subject

import jakarta.persistence.*


@Entity
@Table(name = "subjects")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(unique = true, name = "code")
    val code: String? = null,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "description")
    val description: String? = null,

    @Column(name = "category")
    val category: String? = null
)
