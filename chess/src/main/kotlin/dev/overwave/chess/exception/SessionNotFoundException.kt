package dev.overwave.chess.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class SessionNotFoundException(id: Long) : ResponseStatusException(HttpStatus.NOT_FOUND, "Session $id not found")
