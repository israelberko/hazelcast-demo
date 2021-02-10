package com.k1.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories

@SpringBootApplication
//@EnableConfigurationProperties
@EnableCaching
@EnableHazelcastRepositories(basePackages = ["com.k1.demo.repository"])
class K1ServiceApplication

fun main(args: Array<String>) {
	runApplication<K1ServiceApplication>(*args)
}
