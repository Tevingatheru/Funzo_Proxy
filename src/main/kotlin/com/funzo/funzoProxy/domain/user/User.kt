package com.funzo.funzoProxy.domain.user

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true, name = "code")
    val code: String? = null,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    val type: UserType? = null,

    @Column(name = "email")
    val email: String? = null
) {
    constructor(code: String, userType: UserType, email: String) : this(
        null, code = code, type = userType, email = email
    )
}
