package dev.overwave.chess.service

import dev.overwave.chess.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>