package dev.overwave.chess.mapper

import dev.overwave.chess.dto.CreatedLobbyResponseDto
import dev.overwave.chess.dto.OpenLobbyDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.Side
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.model.Lobby
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.User

fun toCreatedLobbyResponseDto(session: Session): CreatedLobbyResponseDto {
    return CreatedLobbyResponseDto(
        null,
        session.id
    )
}

fun toCreatedLobbyResponseDto(lobby: Lobby): CreatedLobbyResponseDto {
    return CreatedLobbyResponseDto(
        lobby.id,
        null
    )
}

fun toSimpleSessionResponseDto(session: Session): SimpleSessionResponseDto {
    return SimpleSessionResponseDto(
        session.id,
        session.status
    )
}

fun toOpenLobbyDto(lobby: Lobby): OpenLobbyDto {
    val opponent = lobby.user
    val opponentSide = lobby.side
    return OpenLobbyDto(
        lobby.id,
        toPlayerDto(opponent),
        opponentSide
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
