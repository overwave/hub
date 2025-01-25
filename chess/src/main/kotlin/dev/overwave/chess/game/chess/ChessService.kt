package dev.overwave.chess.game.chess

import dev.overwave.chess.dto.Side
import dev.overwave.chess.game.chess.dto.ChessBoard
import dev.overwave.chess.game.chess.dto.ChessGame
import dev.overwave.chess.game.chess.dto.TurnRequest
import dev.overwave.chess.game.core.Game
import dev.overwave.chess.game.core.GameRepository
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
    fun startGame(): ChessGame {
        val user = userRepository.doFindByLogin("user")
        val game = gameRepository.save(Game(whitePlayer = user, blackPlayer = user))
        val pieces =
            chessLayout
                .getStartLayout()
                .map { (position, type, side) ->
                    Piece(
                        game = game,
                        position = position,
                        type = type,
                        side = side,
                    )
                }
        val chessPieces = pieceRepository.saveAll(pieces).map { pieceMapper.toChessPiece(it) }
        return ChessGame(game.id, Side.WHITE, ChessBoard(chessPieces))
    }

    fun makeTurn(request: TurnRequest): ChessGame {
        val game = gameRepository.findInProgressGame(request.gameId)

        val piece = game[request.from] ?: throw NoSuchElementException(request.from.toString())
        piece.position = request.to
        game.status = game.status.next
        gameRepository.save(game)

        val chessPieces = game.pieces.map { pieceMapper.toChessPiece(it) }
        return ChessGame(game.id, Side.BLACK, ChessBoard(chessPieces))
    }
}
