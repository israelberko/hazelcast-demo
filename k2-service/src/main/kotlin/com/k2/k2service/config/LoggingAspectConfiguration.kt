package com.k2.k2service.config

import com.k2.k2service.logging.LoggingAspect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.core.env.Environment

@Configuration
@EnableAspectJAutoProxy
class LoggingAspectConfiguration {

    @Bean
    fun loggingAspect(env: Environment) = LoggingAspect(env)
}
