package dev.overwave.chess.game.core

import dev.overwave.chess.model.LongGenAud
import dev.overwave.chess.model.User
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.JdbcType
import org.hibernate.dialect.PostgreSQLEnumJdbcType
import java.time.Instant

@Entity
class Game(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "white_player_id")
    var whitePlayer: User,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "black_player_id")
    var blackPlayer: User,
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType::class)
    var status: GameStatus,
    var finishedAt: Instant?,
) : LongGenAud() {
    override fun toString(): String = "Session(id=$id, status=$status)"
}
