package dev.overwave.chess.game.core

import dev.overwave.chess.dto.Side
import dev.overwave.chess.model.LongGenAud
import dev.overwave.chess.service.PieceType
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.JdbcType
import org.hibernate.dialect.PostgreSQLEnumJdbcType

@Entity
class Piece(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    val game: Game,
    var taken: Boolean = false,
    var promoted: Boolean = false,
    var position: String,
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType::class)
    var type: PieceType,
    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType::class)
    var side: Side,
) : LongGenAud()
