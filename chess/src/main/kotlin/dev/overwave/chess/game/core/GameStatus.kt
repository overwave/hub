package dev.overwave.chess.game.core

enum class GameStatus {
    WHITES_TURN,
    BLACKS_TURN,
    FINISHED,
    CANCELLED,
    ;

    val inProgress: Boolean by lazy { this in setOf(WHITES_TURN, BLACKS_TURN) }

    val next: GameStatus by lazy {
        when (this) {
            WHITES_TURN -> BLACKS_TURN
            BLACKS_TURN -> WHITES_TURN
            else -> throw IllegalStateException("Game not in progress")
        }
    }
}
