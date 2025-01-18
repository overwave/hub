package dev.overwave.chess.game.chess

import dev.overwave.chess.dto.Side
import dev.overwave.chess.service.PieceType
import org.springframework.stereotype.Repository

@Repository
class ChessLayout {
    private val startSideLayout =
        listOf(
            PieceType.ROOK to 1,
            PieceType.KNIGHT to 2,
            PieceType.BISHOP to 3,
            PieceType.QUEEN to 4,
            PieceType.KING to 5,
            PieceType.BISHOP to 6,
            PieceType.KNIGHT to 7,
            PieceType.ROOK to 8,
        )

    fun getStartLayout(): List<Triple<Position, PieceType, Side>> {
        val whiteFigures = startSideLayout.map { (type, x) -> Triple(Position(x = x, y = 1), type, Side.WHITE) }
        val whitePawns = (1..8).map { x -> Triple(Position(x = x, y = 2), PieceType.PAWN, Side.WHITE) }
        val blackPawns = (1..8).map { x -> Triple(Position(x = x, y = 7), PieceType.PAWN, Side.BLACK) }
        val blackFigures = startSideLayout.map { (type, x) -> Triple(Position(x = x, y = 8), type, Side.BLACK) }

        return whiteFigures + whitePawns + blackPawns + blackFigures
    }
}
