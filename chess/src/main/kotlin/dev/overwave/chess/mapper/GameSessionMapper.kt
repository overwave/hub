package dev.overwave.chess.mapper

import dev.overwave.chess.dto.GameSessionResponseDto
import dev.overwave.chess.model.GameSession
import org.springframework.stereotype.Service

@Service
class GameSessionMapper () {
    fun toGameSessionResponseDto(gameSession: GameSession) : GameSessionResponseDto {
        return GameSessionResponseDto(gameSession.id,
            gameSession.whitePlayer.ip,
            gameSession.blackPlayer.ip,
            gameSession.status)
    }
}
