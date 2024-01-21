package dev.overwave.chess.repository

import dev.overwave.chess.exception.SessionNotFoundException
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository("gameSessionRepository")
interface SessionRepository : JpaRepository<Session, Long> {
    fun findAllByStatus(status: SessionStatus): List<Session>
}

fun SessionRepository.findByIdOrThrow(id: Long): Session {
    return findByIdOrNull(id) ?: throw SessionNotFoundException(id)
}