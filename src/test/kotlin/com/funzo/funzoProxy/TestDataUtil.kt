package com.funzo.funzoProxy

import com.funzo.funzoProxy.domain.exam.Exam
import com.funzo.funzoProxy.domain.result.Result
import com.funzo.funzoProxy.domain.user.User
import com.funzo.funzoProxy.domain.user.UserType

object TestDataUtil {

    object ResultData {
        const val score = 0.0
        const val resultCode = "R01"
        const val userCode = "U01"
        val student = User(id = 1L, code = userCode, type = UserType.STUDENT, email = "student@emil.com")
        val exam = Exam(level = 1)
        val result = Result(
            id = 1L,
            exam = exam,
            student = student,
            code = resultCode,
            score = score,
            attemptNo = 0
        )
        const val examCode = "E01"
    }

}