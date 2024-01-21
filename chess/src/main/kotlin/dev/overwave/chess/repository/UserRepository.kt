package dev.overwave.chess.repository

import dev.overwave.chess.exception.UserNotFoundException
import dev.overwave.chess.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(login: String): User?
}

fun UserRepository.findByLoginOrThrow(login: String): User {
    return findByLogin(login) ?: throw UserNotFoundException(login)
}
