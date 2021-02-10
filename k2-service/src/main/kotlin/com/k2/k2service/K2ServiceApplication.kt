package com.k2.k2service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class K2ServiceApplication

fun main(args: Array<String>) {
	runApplication<K2ServiceApplication>(*args)
}
