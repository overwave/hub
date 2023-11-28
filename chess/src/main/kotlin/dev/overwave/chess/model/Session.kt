package dev.overwave.chess.model

import jakarta.persistence.Entity

@Entity
class Session : LongGenAud() {
    var testField: String = ""

    override fun toString() = "Session(id=$id, testField='$testField')"
}