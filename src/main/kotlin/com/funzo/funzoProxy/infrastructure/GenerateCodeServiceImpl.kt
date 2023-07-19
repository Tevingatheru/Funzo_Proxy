package com.funzo.funzoProxy.infrastructure

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GenerateCodeServiceImpl : GenerateCodeService {
    override fun generateCodeWithLength(length: Int): String {
        return generateCode().substring(0, length)
    }

    private fun generateCode(): String {
        return UUID.randomUUID().toString().replace("-","")
    }
}
