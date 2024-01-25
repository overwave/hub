package dev.overwave.chess.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class BotNotFoundException : ResponseStatusException(HttpStatus.NOT_FOUND, "Bot not found")