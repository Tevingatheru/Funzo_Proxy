package com.funzo.funzoProxy.infrastructure.dao

import com.funzo.funzoProxy.domain.user.UserType
import jakarta.persistence.Tuple


data class UserCountDao(
    val type: UserType? = null,
    val count: Long? = null
) {

}
