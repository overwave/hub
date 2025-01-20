package dev.overwave.chess.game.chess.dto

import dev.overwave.chess.dto.Side
import dev.overwave.chess.game.dto.Position
import dev.overwave.chess.service.PieceType

data class ChessPiece(
    val position: Position,
    val type: PieceType,
    val side: Side,
)
