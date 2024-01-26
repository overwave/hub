package dev.overwave.chess.mapper

import dev.overwave.chess.dto.OpenSessionDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.Side
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.User

fun toSimpleSessionResponseDto(session: Session): SimpleSessionResponseDto {
    return SimpleSessionResponseDto(
        session.id,
        session.status
    )
}

fun toOpenSessionDto(session: Session): OpenSessionDto {
    val (opponent, opponentSide) = getOpponentAndSide(session.whitePlayer, session.blackPlayer)
    return OpenSessionDto(
        session.id,
        toPlayerDto(opponent),
        if (opponent.bot) Side.ANY else opponentSide
    )
}

private fun getOpponentAndSide(white: User?, black: User?): Pair<User, Side> {
    return if (white != null) {
        white to Side.WHITE
    } else if (black != null) {
        black to Side.BLACK
    } else {
        throw IllegalStateException()
    }
}

fun toPlayableSessionResponseDto(): PlayableSessionResponseDto {
    TODO()
}
