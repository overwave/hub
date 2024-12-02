package dev.overwave.chess.repository

import dev.overwave.chess.model.SessionMessage
import org.springframework.data.jpa.repository.JpaRepository

interface SessionMessageRepository : JpaRepository<SessionMessage, Long> {
    fun findAllBySessionId(sessionId: Long): List<SessionMessage>
}
