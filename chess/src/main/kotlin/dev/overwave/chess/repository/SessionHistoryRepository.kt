package dev.overwave.chess.repository

import dev.overwave.chess.model.SessionHistory
import org.springframework.data.jpa.repository.JpaRepository

interface SessionHistoryRepository : JpaRepository<SessionHistory, Long> {
    fun findAllBySessionId(sessionId: Long): List<SessionHistory>
}
