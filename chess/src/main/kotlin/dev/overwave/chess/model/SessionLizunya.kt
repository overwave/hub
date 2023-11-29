package dev.overwave.chess.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "sessions")
class SessionLizunya(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "white_ip", nullable = false)
    val whiteIp: String,

    @Column(name = "black_ip", nullable = false)
    val blackIp: String,
) {
    override fun equals(other: Any?) = if (other !is SessionLizunya) false else other.id == id

    override fun hashCode() = id.hashCode()

    override fun toString(): String {
        return "SessionLizunya(id=$id, whiteIp='$whiteIp', blackIp='$blackIp')"
    }
}