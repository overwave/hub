package dev.overwave.chess.model

import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.FigureType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Figure(

    @Enumerated(EnumType.STRING)
    val type: FigureType,

    @Enumerated(EnumType.STRING)
    val color: FigureColor,

    val address: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session

) : LongGenAud() {

    override fun toString(): String {
        return "Figure(id=$id, type=$type, color=$color, address='$address', session=${session.id})"
    }
}