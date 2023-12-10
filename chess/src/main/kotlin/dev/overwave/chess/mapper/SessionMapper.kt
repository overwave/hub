package dev.overwave.chess.mapper

import dev.overwave.chess.dto.SessionResponseDto
import dev.overwave.chess.model.Session

fun toSessionResponseDto(session: Session): SessionResponseDto {
    return SessionResponseDto(
        session.id,
        session.whitePlayer?.ip,
        session.blackPlayer?.ip,
        session.status
    )
}
