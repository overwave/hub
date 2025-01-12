package dev.overwave.chess.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@MappedSuperclass
open class LongGenAud(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    @CreationTimestamp
    @Column(name = "created_at")
    val createdAt: Instant = Instant.now(),
    @Version
    @Column(name = "updated_at")
    var updatedAt: Instant = Instant.now(),
) {
    override fun equals(other: Any?) = if (other is LongGenAud) other.id == id else false

    override fun hashCode() = id.hashCode()

    override fun toString() = "${this::class.simpleName}[$id]"
}
