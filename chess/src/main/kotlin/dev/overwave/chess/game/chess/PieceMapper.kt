package dev.overwave.chess.game.chess

import dev.overwave.chess.game.core.Piece
import org.springframework.stereotype.Component

@Component
class PieceMapper {
    fun toChessPiece(piece: Piece): ChessPiece {
        assert(piece.type.chess) { "Piece type must be chess, ${piece.type} providen" }
        val position = piece.position.toPosition()
        assert(position.x < 9 && position.y < 9) { "Position must be in range [1, 8]" }

        return ChessPiece(
            position = position,
            type = piece.type,
            side = piece.side,
        )
    }
}
