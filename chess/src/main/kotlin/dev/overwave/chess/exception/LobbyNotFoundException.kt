package dev.overwave.chess.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class LobbyNotFoundException(
    id: Long,
) : ResponseStatusException(HttpStatus.NOT_FOUND, "Lobby $id not found")
