package dev.overwave.chess.core

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service

@Service
class Dummy : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        println("hello!")
        while (true) {
            val line = readLine()
            if (line.isNullOrEmpty()) break
            println("you've printed $line")
        }
        println("bye-bye!")
    }
}