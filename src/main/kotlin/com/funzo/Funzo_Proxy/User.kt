package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(unique = true, name = "code")
    val code: String,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType,

    @Column(name = "email")
    val email: String
)
