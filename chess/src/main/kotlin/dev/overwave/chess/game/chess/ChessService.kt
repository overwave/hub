package dev.overwave.chess.game.chess

import dev.overwave.chess.game.chess.dto.ChessBoard
import dev.overwave.chess.game.core.Game
import dev.overwave.chess.game.core.GameRepository
import dev.overwave.chess.game.core.GameStatus
import dev.overwave.chess.game.core.Piece
import dev.overwave.chess.game.core.PieceRepository
import dev.overwave.chess.repository.UserRepository
import dev.overwave.chess.repository.doFindByLogin
import org.springframework.stereotype.Service

@Service
class ChessService(
    private val gameRepository: GameRepository,
    private val pieceRepository: PieceRepository,
    private val userRepository: UserRepository,
    private val chessLayout: ChessLayout,
    private val pieceMapper: PieceMapper,
) {
    fun startGame(): ChessBoard {
        val user = userRepository.doFindByLogin("user")
        val game =
            gameRepository.save(
                Game(
                    whitePlayer = user,
                    blackPlayer = user,
                    status = GameStatus.WHITES_TURN,
                    finishedAt = null,
                ),
            )
        val pieces =
            chessLayout
                .getStartLayout()
                .map { (position, type, side) ->
                    Piece(
                        game = game,
                        position = position.toString(),
                        type = type,
                        side = side,
                    )
                }
        val chessPieces = pieceRepository.saveAll(pieces).map { pieceMapper.toChessPiece(it) }
        return ChessBoard(chessPieces)
    }
}
