package com.funzo.funzoProxy.domain.user

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(unique = true, name = "code")
    val code: String,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType,

    @Column(name = "email")
    val email: String
)
