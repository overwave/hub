package dev.overwave.chess.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class SessionNotOpenedException(id: Long) : ResponseStatusException(HttpStatus.CONFLICT, "Session $id is not opened")