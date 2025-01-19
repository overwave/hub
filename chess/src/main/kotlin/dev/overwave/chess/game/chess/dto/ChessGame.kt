package dev.overwave.chess.game.chess.dto

import dev.overwave.chess.dto.Side

data class ChessGame(
    val gameId: Long,
    val turningSide: Side,
    val chessBoard: ChessBoard,
)
