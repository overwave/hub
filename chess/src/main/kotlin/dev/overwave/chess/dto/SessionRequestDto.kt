package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.service.FigureColor

data class SessionRequestDto(
    val id: Long,
    val opponent: PlayerDto,
    val availableSide: FigureColor,
    val status: SessionStatus,
)
