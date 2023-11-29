package dev.overwave.chess.model

import dev.overwave.chess.service.FigureColor
import dev.overwave.chess.service.FigureType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "figures")
class Figure(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    val type: FigureType,

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    val color: FigureColor,

    @Column(name = "address", nullable = false)
    val address: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    val session: Session
) {
    override fun equals(other: Any?) = if (other !is Figure) false else other.id == id

    override fun hashCode() = id.hashCode()

    override fun toString(): String {
        return "Figure(id=$id, type=$type, color=$color, address='$address', session=$session)"
    }
}