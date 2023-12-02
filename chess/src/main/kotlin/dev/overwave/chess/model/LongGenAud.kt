package dev.overwave.chess.model

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass

@MappedSuperclass
open class LongGenAud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

) {

    override fun equals(other: Any?) = if (other !is LongGenAud) false else other.id == id

    override fun hashCode() = id.hashCode()

    override fun toString() = "${this::class.simpleName}[$id]"
}