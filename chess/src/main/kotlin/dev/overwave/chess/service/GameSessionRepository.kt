package dev.overwave.chess.service

import dev.overwave.chess.model.Session
import org.springframework.data.jpa.repository.JpaRepository

interface GameSessionRepository : JpaRepository<Session, Long> {
}