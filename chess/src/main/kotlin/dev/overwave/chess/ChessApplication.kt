package dev.overwave.chess

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChessApplication

fun main(args: Array<String>) {
    runApplication<ChessApplication>(*args)
}
