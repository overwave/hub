package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user_")
class User(
    val login: String,
    var name: String,
    @Transient
    @Deprecated("к удалению", level = DeprecationLevel.ERROR)
    val ip: String,
    var password: String,
    val bot: Boolean,
) : LongGenAud()