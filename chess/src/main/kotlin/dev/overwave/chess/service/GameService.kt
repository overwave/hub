package dev.overwave.chess.service

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.FigureDto
import dev.overwave.chess.dto.GameSessionRequestDto
import dev.overwave.chess.dto.GameSessionResponseDto
import dev.overwave.chess.dto.TileDto
import dev.overwave.chess.mapper.GameSessionMapper
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.repository.GameSessionRepository
import org.springframework.stereotype.Service

@Service
class GameService(
    private val gameSessionMapper: GameSessionMapper,
    private val gameSessionRepository: GameSessionRepository
) {
    private val figureLayout = mapOf(
        "a" to FigureType.ROOK,
        "b" to FigureType.KNIGHT,
        "c" to FigureType.BISHOP,
        "d" to FigureType.QUEEN,
        "e" to FigureType.KING,
        "f" to FigureType.BISHOP,
        "g" to FigureType.KNIGHT,
        "h" to FigureType.ROOK
    )

    fun getBoard(): BoardResponseDto {
        val tiles = mutableListOf<TileDto>()
        tiles += figureLayout.map { (row, figure) ->
            TileDto("${row}8", FigureDto(FigureColor.BLACK, figure))
        }
        for (i in 'a'..'h') {
            tiles += TileDto("${i}7", FigureDto(FigureColor.BLACK, FigureType.PAWN))
            tiles += TileDto("${i}2", FigureDto(FigureColor.WHITE, FigureType.PAWN))
        }
        tiles += figureLayout.map { (row, figure) ->
            TileDto("${row}1", FigureDto(FigureColor.WHITE, figure))
        }
        return BoardResponseDto(tiles.associateBy { it.address })
    }

    fun getOpenSessions(): List<GameSessionResponseDto> {
        val sessions = gameSessionRepository.findAllByStatus(SessionStatus.OPEN)
        return sessions.map { gameSessionMapper.toGameSessionResponseDto(it) }
    }

    fun startGame(id: Int, gameSession: GameSessionRequestDto): GameSessionResponseDto {
        val white: Int
        val black: Int
        if(gameSession.side == FigureColor.WHITE) {
            white = id;
            black = gameSession.against
        } else {
            white = gameSession.against
            black = id
        }
        //sessionRepository.save(Session(-1, ))
        TODO("Not yet implemented")
    }
}
