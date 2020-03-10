package ru.tinkoff.fintech

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.kafka.annotation.EnableKafka


@SpringBootApplication
@EnableKafka
open class FintechApplication {
    fun main(args: Array<String>) {
        SpringApplication.run(FintechApplication::class.java, *args)
    }
}