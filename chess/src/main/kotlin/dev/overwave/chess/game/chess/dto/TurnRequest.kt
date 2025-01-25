package dev.overwave.chess.game.chess.dto

import dev.overwave.chess.game.dto.Position

data class TurnRequest(
    val gameId: Long,
    val from: Position,
    val to: Position,
)
