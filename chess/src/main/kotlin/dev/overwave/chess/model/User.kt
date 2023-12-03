package dev.overwave.chess.model

import jakarta.persistence.Entity

@Entity
class User(

    val ip: String,

    val name: String,

    val bot: Boolean

) : LongGenAud() {
}