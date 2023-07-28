package com.funzo.funzoProxy.infrastructure

import org.slf4j.LoggerFactory


class LoggerUtils {
    companion object {
        private fun buildLogMessage(message: String, map: Map<String, Any>?): String {
            val stringBuilder = StringBuilder()
            stringBuilder.appendLine(message)
            map?.forEach { (key, value) ->
                stringBuilder.appendLine("$key : $value")
            }
            return stringBuilder.toString()
        }

        fun log(level: LogLevel, message: String, diagnosisMap: Map<String, Any>? = null, className: Class<*>) {
            val logger = LoggerFactory.getLogger(className)
            when (level) {
                LogLevel.DEBUG -> logger.debug(buildLogMessage(message, diagnosisMap))
                LogLevel.INFO -> logger.info(buildLogMessage(message, diagnosisMap))
                LogLevel.WARN -> logger.warn(buildLogMessage(message, diagnosisMap))
                LogLevel.ERROR -> logger.error(buildLogMessage(message, diagnosisMap))
            }
        }

        fun log(level: LogLevel, message: String, className: Class<*>) {
            log(level, message, null, className)
        }
    }
}

enum class LogLevel {
    DEBUG, INFO, WARN, ERROR
}
