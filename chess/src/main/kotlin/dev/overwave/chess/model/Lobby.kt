package dev.overwave.chess.model

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Lobby(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User,
    @Enumerated(EnumType.STRING)
    var status: LobbyStatus,
    @Enumerated(EnumType.STRING)
    var side: LobbySide,
) : LongGenAud() {
    override fun toString(): String = "Lobby(id=$id, status=$status)"
}
