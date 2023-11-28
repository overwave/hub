package dev.overwave.chess.model

import jakarta.persistence.Entity

@Entity
class Session(
    var testField: String
) : LongGenAud() {

    override fun toString() = "Session(id=$id, testField='$testField')"
}