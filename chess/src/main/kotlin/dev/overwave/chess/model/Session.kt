package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Session(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "white_player_id")
    var whitePlayer: User?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "black_player_id")
    var blackPlayer: User?,
    @Enumerated(EnumType.STRING)
    var status: SessionStatus,
) : LongGenAud() {
    override fun toString(): String = "Session(id=$id, status=$status)"
}
