package com.funzo.funzoProxy.infrastructure.util

import org.slf4j.LoggerFactory

/**
 * Utility class for logging messages with different log levels.
 */
class LoggerUtils {
    companion object {
        /**
         * Builds the log message with additional diagnostic information from the provided map.
         * @param message The main log message.
         * @param map The map containing diagnostic information (can be null).
         * @return The complete log message as a string.
         */
        private fun buildLogMessage(message: String, map: Map<String, Any>?): String {
            val stringBuilder = StringBuilder()
            stringBuilder.appendLine(message)
            map?.forEach { (key, value) ->
                stringBuilder.appendLine("$key : $value")
            }
            return stringBuilder.toString()
        }

        /**
         * Logs a message with the specified log level.
         * @param level The log level (DEBUG, INFO, WARN, or ERROR).
         * @param message The log message.
         * @param diagnosisMap The map containing diagnostic information (can be null).
         * @param className The class for which the log message is being generated.
         */
        fun log(level: LogLevel, message: String, diagnosisMap: Map<String, Any>? = null, className: Class<*>) {
            val logger = LoggerFactory.getLogger(className)
            when (level) {
                LogLevel.DEBUG -> logger.debug(buildLogMessage(message, diagnosisMap))
                LogLevel.INFO -> logger.info(buildLogMessage(message, diagnosisMap))
                LogLevel.WARN -> logger.warn(buildLogMessage(message, diagnosisMap))
                LogLevel.ERROR -> logger.error(buildLogMessage(message, diagnosisMap))
            }
        }

        /**
         * Logs a message with the specified log level (with no additional diagnostic information).
         * @param level The log level (DEBUG, INFO, WARN, or ERROR).
         * @param message The log message.
         * @param className The class for which the log message is being generated.
         */
        fun log(level: LogLevel, message: String, className: Class<*>) {
            log(level, message, null, className)
        }
    }
}

/**
 * Enum representing different log levels.
 */
enum class LogLevel {
    DEBUG, INFO, WARN, ERROR
}
