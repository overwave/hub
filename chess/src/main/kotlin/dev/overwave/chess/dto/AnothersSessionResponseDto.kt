package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionHistory
import dev.overwave.chess.model.SessionMessage
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.service.FigureColor

data class AnothersSessionResponseDto(
    val id: Long,
    val white: String?,
    val black: String?,
    val side: FigureColor,
    val status: SessionStatus,
    val board: BoardResponseDto,
    val turnHistory: List<SessionHistory>,
    val chatHistory: List<SessionMessage>
)
