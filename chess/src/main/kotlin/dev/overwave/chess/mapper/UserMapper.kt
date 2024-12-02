package dev.overwave.chess.mapper

import dev.overwave.chess.dto.PlayerDto
import dev.overwave.chess.model.User

fun toPlayerDto(user: User): PlayerDto =
    PlayerDto(
        user.name,
        user.login,
        user.bot,
    )
