package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class GameSession(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "white_player_id")
    val whitePlayer: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "black_player_id")
    val blackPlayer: User,

    @Enumerated(EnumType.STRING)
    val status: SessionStatus

    ) : LongGenAud() {

    override fun toString(): String {
        return "Session(whitePlayerId=$whitePlayer.id, blackPlayerId=$blackPlayer.id, status=$status)"
    }
}