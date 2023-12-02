package dev.overwave.chess.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity
class Session(

    @Column(name = "white_ip", nullable = false)
    val whiteIp: String,

    @Column(name = "black_ip", nullable = false)
    val blackIp: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: Instant = Instant.now(),

    @Version
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = Instant.now()

) : LongGenAud() {

    override fun toString(): String {
        return "Session(id=$id, whiteIp='$whiteIp', blackIp='$blackIp', createdAt='$createdAt', updatedAt='$updatedAt')"
    }
}