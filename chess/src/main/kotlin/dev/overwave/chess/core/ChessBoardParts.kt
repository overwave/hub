package dev.overwave.chess.core

enum class ChessBoardParts(
    val view: String,
) {
    FRAME_TOP("╔════════════════╗"),
    EMPTY_CELLS_1("║  ██  ██  ██  ██║"),
    EMPTY_CELLS_2("║██  ██  ██  ██  ║"),
    BLACK_FIGURES("♜ ♞ ♝ ♛ ♚ ♝ ♞ ♜"),
    BLACK_PAWNS("♟ ♟ ♟ ♟ ♟ ♟ ♟ ♟ "),
    WHITE_FIGURES(""),
    WHITE_PAWNS(""),
    FRAME_BOTTOM("╚════════════════╝"),
    ALPHABETICAL_INDEXES(" a b c d e f g h"),
}
