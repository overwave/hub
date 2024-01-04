package dev.overwave.chess.security

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class UserNotExistsException(login: String) : ResponseStatusException(HttpStatus.NOT_FOUND, "User $login not found")
