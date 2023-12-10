package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_")
class User(
    val ip: String,
    var name: String,
    val login: String,
    var password: String,
    val bot: Boolean,
) : LongGenAud()