package dev.overwave.chess.security

data class LoginDto(
    val result: LoginStatus
)

enum class LoginStatus {
    SUCCESS,
    FAILED,
}
