package com.funzo.Funzo_Proxy

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.domain.EntityScan


@Entity
@Table(name = "subjects")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @Column(unique = true, name = "code")
    val code: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "category")
    val category: String
)
