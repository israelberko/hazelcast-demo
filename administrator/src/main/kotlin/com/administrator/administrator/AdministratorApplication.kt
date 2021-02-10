package com.administrator.administrator

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableAdminServer
@SpringBootApplication
class AdministratorApplication

fun main(args: Array<String>) {
	runApplication<AdministratorApplication>(*args)
}
