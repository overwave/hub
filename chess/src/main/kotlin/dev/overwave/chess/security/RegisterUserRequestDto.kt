package dev.overwave.chess.security

import jakarta.validation.constraints.NotBlank

data class RegisterUserRequestDto(
    @NotBlank
    val login: String,
    @NotBlank
    val password: String,
)
