package dev.overwave.chess.repository

import dev.overwave.chess.model.GameSession
import dev.overwave.chess.model.SessionStatus
import org.springframework.data.jpa.repository.JpaRepository

interface GameSessionRepository : JpaRepository<GameSession, Long> {
    fun findAllByStatus(status : SessionStatus): List<GameSession>

}