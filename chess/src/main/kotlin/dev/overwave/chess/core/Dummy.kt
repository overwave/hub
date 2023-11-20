package dev.overwave.chess.core

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service

@Service
class Dummy : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val initializer = Initializer()
        initializer.fillBoardLikeAPro()
        initializer.printChessBoardLikePro()

        }
}