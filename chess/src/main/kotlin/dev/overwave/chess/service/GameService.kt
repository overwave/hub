package dev.overwave.chess.service

import dev.overwave.chess.dto.BoardResponseDto
import dev.overwave.chess.dto.CreatedLobbyResponseDto
import dev.overwave.chess.dto.FigureDto
import dev.overwave.chess.dto.LobbyRequestDto
import dev.overwave.chess.dto.OpenLobbiesListDto
import dev.overwave.chess.dto.PlayableSessionResponseDto
import dev.overwave.chess.dto.SimpleSessionResponseDto
import dev.overwave.chess.dto.TileDto
import dev.overwave.chess.exception.BotNotFoundException
import dev.overwave.chess.exception.SessionNotOpenedException
import dev.overwave.chess.mapper.toCreatedLobbyResponseDto
import dev.overwave.chess.mapper.toOpenLobbyDto
import dev.overwave.chess.mapper.toSimpleSessionResponseDto
import dev.overwave.chess.model.Lobby
import dev.overwave.chess.model.LobbySide
import dev.overwave.chess.model.LobbyStatus
import dev.overwave.chess.model.Session
import dev.overwave.chess.model.SessionStatus
import dev.overwave.chess.model.User
import dev.overwave.chess.repository.FigureRepository
import dev.overwave.chess.repository.LobbyRepository
import dev.overwave.chess.repository.SessionHistoryRepository
import dev.overwave.chess.repository.SessionMessageRepository
import dev.overwave.chess.repository.SessionRepository
import dev.overwave.chess.repository.UserRepository
import dev.overwave.chess.repository.findByIdOrThrow
import dev.overwave.chess.repository.findByLoginOrThrow
import org.springframework.stereotype.Service
import kotlin.random.Random

private const val BOT_LOGIN: String = "bot"

@Service
class GameService(
    private val lobbyRepository: LobbyRepository,
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

    fun getOpenLobbies(): OpenLobbiesListDto {
        val lobbies = lobbyRepository.findAllByStatus(LobbyStatus.OPEN)
        return OpenLobbiesListDto(lobbies.map { toOpenLobbyDto(it) })
    }

    fun startGame(login: String, request: LobbyRequestDto): CreatedLobbyResponseDto {
        val user = userRepository.findByLoginOrThrow(login)
        if (request.opponent == Opponent.BOT) {
            val opponent = userRepository.findTop1ByBotIsTrue() ?: throw BotNotFoundException()

            val white: User
            val black: User
            val side = getSide(request.side)

            if (side == LobbySide.WHITE) {
                white = user
                black = opponent
            } else {
                white = opponent
                black = user
            }
            val session = sessionRepository.save(Session(white, black, SessionStatus.IN_PROGRESS))
            return toCreatedLobbyResponseDto(session)
        }
        val lobby = lobbyRepository.save(Lobby(user, LobbyStatus.OPEN, request.side))
        return toCreatedLobbyResponseDto(lobby)
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
        return toSimpleSessionResponseDto(sessionRepository.save(session))
    }

    fun getSession(sessionId: Long): PlayableSessionResponseDto {
        val session = sessionRepository.findByIdOrThrow(sessionId)
        val figures = figureRepository.findAllBySessionId(sessionId)
        val sessionHistory = sessionHistoryRepository.findAllBySessionId(sessionId)
        val sessionMessages = sessionMessageRepository.findAllBySessionId(sessionId)

        TODO("Not yet implemented")
    }

    private fun getSide(side: LobbySide): LobbySide {
        if(side != LobbySide.ANY) {
            return side
        }
        return if (Random.nextBoolean()) LobbySide.BLACK else LobbySide.WHITE
    }
}
