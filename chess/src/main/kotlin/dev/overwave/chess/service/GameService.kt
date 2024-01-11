package dev.overwave.chess.service

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.FigureDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.dto.TileDto
import dev.overwave.chess.exception.SessionNotOpenedException
import dev.overwave.chess.mapper.toSessionResponseDto
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.repository.FigureRepository
import dev.overwave.chess.repository.SessionHistoryRepository
import dev.overwave.chess.repository.SessionMessageRepository
import dev.overwave.chess.repository.SessionRepository
import dev.overwave.chess.repository.UserRepository
import dev.overwave.chess.repository.findByIdOrThrow
import dev.overwave.chess.repository.findByLoginOrThrow
import org.springframework.stereotype.Service

const val BOT_LOGIN: String = "bot"

@Service
class GameService(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val figureRepository: FigureRepository,
    private val sessionHistoryRepository: SessionHistoryRepository,
    private val sessionMessageRepository: SessionMessageRepository,
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

    fun getOpenSessions(): List<SimpleSessionResponseDto> {
        val sessions = sessionRepository.findAllByStatus(SessionStatus.OPEN)
        return sessions.map { toSessionResponseDto(it) }
    }

    fun startGame(login: String, request: StartSessionRequestDto): SimpleSessionResponseDto {
        val user = userRepository.findByLoginOrThrow(login)
        val opponent = if (request.opponent == Opponent.BOT) userRepository.findByLoginOrThrow(BOT_LOGIN) else null

        val white = if (request.side == FigureColor.WHITE) user else opponent
        val black = if (request.side == FigureColor.BLACK) user else opponent

        val status = if (request.opponent == Opponent.BOT) SessionStatus.IN_PROGRESS else SessionStatus.OPEN
        val session = sessionRepository.save(Session(white, black, status))
        return toSessionResponseDto(session)
    }

    fun joinSession(login: String, sessionId: Long): SimpleSessionResponseDto {
        val user = userRepository.findByLoginOrThrow(login)
        val session = sessionRepository.findByIdOrThrow(sessionId)
        if (session.status != SessionStatus.OPEN) {
            throw SessionNotOpenedException(sessionId)
        }
        session.blackPlayer = session.blackPlayer ?: user
        session.whitePlayer = session.whitePlayer ?: user
        session.status = SessionStatus.IN_PROGRESS
        return toSessionResponseDto(sessionRepository.save(session))
    }

    fun getSession(sessionId: Long): PlayableSessionResponseDto {
        val session = sessionRepository.findByIdOrThrow(sessionId)
        val figures = figureRepository.findAllBySessionId(sessionId)
        val sessionHistory = sessionHistoryRepository.findAllBySessionId(sessionId)
        val sessionMessages = sessionMessageRepository.findAllBySessionId(sessionId)

        TODO("Not yet implemented")
    }
}
