package dev.overwave.chess.repository
import dev.overwave.chess.model.Figure
import org.springframework.data.jpa.repository.JpaRepository

interface FigureRepository : JpaRepository<Figure, Long> {
    fun findAllBySessionId(sessionId: Long): List<Figure>
}
