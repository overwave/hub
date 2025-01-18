package dev.overwave.chess.game.core

import org.springframework.data.jpa.repository.JpaRepository

interface PieceRepository : JpaRepository<Piece, Long>
