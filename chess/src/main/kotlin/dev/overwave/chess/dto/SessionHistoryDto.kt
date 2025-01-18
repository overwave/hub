package dev.overwave.chess.dto

import dev.overwave.chess.model.Figure
import dev.overwave.chess.service.PieceType

data class SessionHistoryDto(
    val sessionId: Long,
    val figure: Figure,
    val file: String,
    val rank: Int,
    val type: PieceType,
    val taken: Boolean,
)
