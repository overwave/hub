package dev.overwave.chess.repository

import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("gameSessionRepository")
interface SessionRepository : JpaRepository<Session, Long> {
    fun findAllByStatus(status : SessionStatus): List<Session>
}