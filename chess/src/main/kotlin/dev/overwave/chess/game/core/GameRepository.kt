package dev.overwave.chess.game.core

import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.optionals.getOrNull

interface GameRepository : JpaRepository<Game, Long> {
    fun findInProgressGame(gameId: Long): Game {
        val game = findById(gameId).getOrNull() ?: throw NoSuchElementException("Game not found")
        if (!game.status.inProgress) throw IllegalStateException("Game not in progress")
        return game
    }
}
