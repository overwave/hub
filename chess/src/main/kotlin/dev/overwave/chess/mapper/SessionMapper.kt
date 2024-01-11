package dev.overwave.chess.mapper

import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.model.Session

fun toSessionResponseDto(session: Session): SimpleSessionResponseDto {
    return SimpleSessionResponseDto(
        session.id,
        session.status
    )
}
