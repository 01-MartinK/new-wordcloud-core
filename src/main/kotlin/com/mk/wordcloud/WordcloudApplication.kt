package com.mk.wordcloud

import com.mk.wordcloud.service.StorageService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WordcloudApplication

@Bean
fun init(storageService: StorageService) = CommandLineRunner {
	storageService.deleteAll()
	storageService.init()
}

fun main(args: Array<String>) {
	runApplication<WordcloudApplication>(*args)
}
