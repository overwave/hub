package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_")
class User(
    val name: String,
    val login: String,
    val password: String,
    val bot: Boolean,
) : LongGenAud()