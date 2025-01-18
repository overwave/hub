package dev.overwave.chess.service

enum class PieceType {
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING,
    STONE,
    GOLD_GENERAL,
    SILVER_GENERAL,
    LANCE,
    ;

    val chess: Boolean by lazy { this in setOf(PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING) }
}
