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

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: UserType? = null,

    @Column(name = "email", nullable = false)
    var email: String? = null

) {
    constructor(code: String, userType: UserType, email: String) : this(
        null, code = code, type = userType, email = email
    )
}
