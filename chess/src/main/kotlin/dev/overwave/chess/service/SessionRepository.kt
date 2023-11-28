package dev.overwave.chess.service

import dev.overwave.chess.model.Session
import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long> {
}