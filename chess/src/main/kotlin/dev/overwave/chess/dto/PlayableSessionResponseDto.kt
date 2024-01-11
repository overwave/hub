package dev.overwave.chess.dto

import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.service.FigureColor

data class PlayableSessionResponseDto(
    val id: Long,
    val opponentLogin: String,
    val playerSide: FigureColor,
    val movingSide: FigureColor,
    val status: SessionStatus,
    val board: BoardResponseDto,
    val turnHistory: List<SessionHistoryDto>,
    val chatHistory: List<SessionMessageDto>
) {
}