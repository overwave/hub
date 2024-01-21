package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus

data class SessionRequestDto(
    val id: Long,
    val opponent: PlayerDto,
    val opponentSide: Side,
    val status: SessionStatus,
)
