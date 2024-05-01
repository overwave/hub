package dev.overwave.chess.repository

import dev.overwave.chess.exception.LobbyNotFoundException
import dev.overwave.chess.model.Lobby
import dev.overwave.chess.model.LobbyStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
interface LobbyRepository : JpaRepository<Lobby, Long> {
    fun findAllByStatus(status: LobbyStatus): List<Lobby>

    fun LobbyRepository.findByIdOrThrow(id: Long): Lobby {
        return findByIdOrNull(id) ?: throw LobbyNotFoundException(id)
    }
}