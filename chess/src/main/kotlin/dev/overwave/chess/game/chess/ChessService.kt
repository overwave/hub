package dev.overwave.chess.game.chess

import dev.overwave.chess.game.core.Game
import dev.overwave.chess.game.core.GameRepository
import dev.overwave.chess.game.core.GameStatus
import dev.overwave.chess.repository.UserRepository
import dev.overwave.chess.repository.findByLoginOrThrow
import org.springframework.stereotype.Service

@Service
class ChessService(
    private val gameRepository: GameRepository,
    private val userRepository: UserRepository,
) {
    fun startGame(): Any {
        val user = userRepository.findByLoginOrThrow("user")
        gameRepository.save(
            Game(
                whitePlayer = user,
                blackPlayer = user,
                status = GameStatus.WHITES_TURN,
                finishedAt = null,
            ),
        )
        // create pieces
        return ChessBoard(listOf(ChessPiece("a2".toPosition(), "PAWN", "WHITE")))
    }
}
