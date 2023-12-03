package dev.overwave.chess.model

import dev.overwave.chess.service.FigureType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class SessionHistory(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "figure_id")
    val figure: Figure,

    val file: String,

    val rank: Int,

    @Enumerated(EnumType.STRING)
    val type: FigureType,

    val taken: Boolean,

    ) : LongGenAud() {}