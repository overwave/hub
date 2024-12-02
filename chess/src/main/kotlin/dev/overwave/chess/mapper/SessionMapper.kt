package dev.overwave.chess.mapper

import dev.overwave.chess.dto.CreatedLobbyResponseDto
import dev.overwave.chess.dto.OpenLobbyDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.Side
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.model.Lobby
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.User

fun toCreatedLobbyResponseDto(session: Session): CreatedLobbyResponseDto =
    CreatedLobbyResponseDto(
        null,
        session.id,
    )

fun toCreatedLobbyResponseDto(lobby: Lobby): CreatedLobbyResponseDto =
    CreatedLobbyResponseDto(
        lobby.id,
        null,
    )

fun toSimpleSessionResponseDto(session: Session): SimpleSessionResponseDto =
    SimpleSessionResponseDto(
        session.id,
        session.status,
    )

fun toOpenLobbyDto(lobby: Lobby): OpenLobbyDto {
    val opponent = lobby.user
    val opponentSide = lobby.side
    return OpenLobbyDto(
        lobby.id,
        toPlayerDto(opponent),
        opponentSide,
    )
}

private fun getOpponentAndSide(white: User?, black: User?): Pair<User, Side> =
    if (white != null) {
        white to Side.WHITE
    } else if (black != null) {
        black to Side.BLACK
    } else {
        throw IllegalStateException()
    }

fun toPlayableSessionResponseDto(): PlayableSessionResponseDto {
    TODO()
}
