package com.k2.k2service.config

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories("com.k2.k2service.repository")
@Import(value = [MongoAutoConfiguration::class])
class DatabaseConfiguration {

    private val log = LoggerFactory.getLogger(javaClass)


}
