package dev.overwave.chess.core

class Initializer {

    private val greeting = "Hello, let's play chess!"

    fun printChessBoard() {
        println(greeting)
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)
        for(i in 8 downTo 2 step 2) {
            println("${ChessBoardParts.EMPTY_CELLS_1.view} $i")
            println("${ChessBoardParts.EMPTY_CELLS_2.view} ${i - 1}")
        }
        println(ChessBoardParts.ALPHABETICAL_INDEXES.view)
    }


}