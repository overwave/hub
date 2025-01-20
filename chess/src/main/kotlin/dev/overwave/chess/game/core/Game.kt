package dev.overwave.chess.game.core

import dev.overwave.chess.game.dto.Position
import dev.overwave.chess.model.LongGenAud
import dev.overwave.chess.model.User
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.hibernate.annotations.JdbcType
import org.hibernate.dialect.PostgreSQLEnumJdbcType
import java.time.Instant

@Entity
class Game(
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "white_player_id")
    var whitePlayer: User,
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "black_player_id")
    var blackPlayer: User,
    //
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType::class)
    var status: GameStatus = GameStatus.WHITES_TURN,
    //
    var finishedAt: Instant? = null,
    //
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    val pieces: List<Piece> = ArrayList(),
    //
) : LongGenAud() {
    operator fun get(position: Position): Piece? = position.let { pos -> pieces.find { pos == it.position } }
}
