package com.mk.wordcloud

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordcloudApplication

fun main(args: Array<String>) {
	runApplication<WordcloudApplication>(*args)
}
