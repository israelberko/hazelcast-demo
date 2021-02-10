package com.k1.demo.config

import ch.qos.logback.classic.LoggerContext
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

/*
 * Configures the console and Logstash log appenders from the app properties.
 */
@Configuration
class LoggingConfiguration(
    @Value("\${spring.application.name}") appName: String,
    @Value("\${server.port}") serverPort: String,
    mapper: ObjectMapper
) {
    init {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext

        val map = mutableMapOf<String, String?>()
        map["app_name"] = appName
        map["app_port"] = serverPort


    }
}
