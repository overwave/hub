package dev.overwave.chess.mapper

import dev.overwave.chess.dto.PlayerDto
import dev.overwave.chess.model.User

fun toPlayerDto(user: User): PlayerDto {
    return PlayerDto(
        user.name,
        user.login,
        user.bot
    )
}