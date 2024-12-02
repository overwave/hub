package dev.overwave.chess.service

import dev.overwave.chess.dto.FigureDto
import dev.overwave.chess.dto.StartSessionRequestDto
import dev.overwave.chess.dto.TileDto
import dev.overwave.chess.exception.BotNotFoundException
import dev.overwave.chess.exception.SessionNotFoundException
import dev.overwave.chess.exception.SessionNotOpenedException
import dev.overwave.chess.exception.UserNotFoundException
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
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

//class GameServiceTest {
//    private val user2: User = User("login2", "name2", "password2", false)
//    private val user1: User = User("login1", "name1", "password1", false)
//    private val bot: User = User("bot", "botName", "botPassword", true)
//
//    private val lobbyRepository = mockk<LobbyRepository>()
//    private val sessionRepository = mockk<SessionRepository>()
//    private val userRepository = mockk<UserRepository>()
//    private val figureRepository = mockk<FigureRepository>()
//    private val sessionMessageRepository = mockk<SessionMessageRepository>()
//    private val sessionHistoryRepository = mockk<SessionHistoryRepository>()
//    private val gameService =
//        GameService(
//            lobbyRepository,
//            sessionRepository,
//            userRepository,
//            figureRepository,
//            sessionHistoryRepository,
//            sessionMessageRepository,
//        )
//
//    private fun getOpenedSession(): Session = Session(user1, null, SessionStatus.OPEN)
//
//    private fun getSessionWithBot(): Session = Session(user1, bot, SessionStatus.IN_PROGRESS)
//
//    private fun getSessionWithPlayer(): Session = Session(user1, user2, SessionStatus.IN_PROGRESS)
//
//    @Test
//    fun whenGetOpenSessions_thenOpenSessionsReturned() {
//        every {
//            sessionRepository.findAllByStatus(SessionStatus.OPEN)
//        } returns listOf(getOpenedSession())
//
//        val actual = gameService.getOpenLobbies().openLobbies
//
//        verify(exactly = 1) {
//            sessionRepository.findAllByStatus(SessionStatus.OPEN)
//        }
//        assertThat(actual).hasSize(1)
//    }
//
//    @Test
//    fun whenStartGameWithPlayer_thenSessionOpened() {
//        val login = "login1"
//        val request = StartSessionRequestDto(FigureColor.WHITE, Opponent.PLAYER)
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user1
//
//        every {
//            sessionRepository.save(getOpenedSession())
//        } returns getOpenedSession()
//
//        val actual = gameService.startGame(login, request)
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            sessionRepository.save(getOpenedSession())
//        }
//
//        assertThat(actual.status).isEqualTo(SessionStatus.OPEN)
//    }
//
//    @Test
//    fun whenStartGameWithPlayerAndUserNotFound_thenThrowUserNotFoundException() {
//        val login = "unknown_user"
//        val request = StartSessionRequestDto(FigureColor.WHITE, Opponent.PLAYER)
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } throws UserNotFoundException(login)
//
//        assertThrows<UserNotFoundException> { gameService.startGame(login, request) }
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 0) {
//            sessionRepository.save(any())
//        }
//    }
//
//    @Test
//    fun whenStartGameWithBotAndBotNotFound_thenThrowUserNotFoundException() {
//        val login = "user1"
//        val request = StartSessionRequestDto(FigureColor.WHITE, Opponent.BOT)
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user1
//
//        every {
//            userRepository.findTop1ByBotIsTrue()
//        } returns null
//
//        assertThrows<BotNotFoundException> { gameService.startGame(login, request) }
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            userRepository.findTop1ByBotIsTrue()
//        }
//        verify(exactly = 0) {
//            sessionRepository.save(any())
//        }
//    }
//
//    @Test
//    fun whenStartGameWithBot_thenSessionInProgress() {
//        val login = "login1"
//        val request = StartSessionRequestDto(FigureColor.WHITE, Opponent.BOT)
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user1
//
//        every {
//            userRepository.findTop1ByBotIsTrue()
//        } returns bot
//
//        every {
//            sessionRepository.save(getSessionWithBot())
//        } returns getSessionWithBot()
//
//        val actual = gameService.startGame(login, request)
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            userRepository.findTop1ByBotIsTrue()
//        }
//        verify(exactly = 1) {
//            sessionRepository.save(getSessionWithBot())
//        }
//
//        assertThat(actual.status).isEqualTo(SessionStatus.IN_PROGRESS)
//    }
//
//    @Test
//    fun whenJoinSession_thenSessionInProgress() {
//        val login = "login2"
//        val sessionId = 1L
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user2
//
//        every {
//            sessionRepository.findByIdOrThrow(sessionId)
//        } returns getOpenedSession()
//
//        every {
//            sessionRepository.save(getSessionWithPlayer())
//        } returns getSessionWithPlayer()
//
//        val actual = gameService.joinSession(login, sessionId)
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            sessionRepository.findByIdOrThrow(sessionId)
//        }
//        verify(exactly = 1) {
//            sessionRepository.save(getSessionWithPlayer())
//        }
//
//        assertThat(actual.status).isEqualTo(SessionStatus.IN_PROGRESS)
//    }
//
//    @Test
//    fun whenJoinSessionAndUserNotFound_thenThrowUserNotFoundException() {
//        val login = "unknown_user"
//        val sessionId = 1L
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } throws UserNotFoundException(login)
//
//        assertThrows<UserNotFoundException> { gameService.joinSession(login, sessionId) }
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 0) {
//            sessionRepository.save(any())
//        }
//    }
//
//    @Test
//    fun whenJoinSessionAndSessionNotFound_thenThrowSessionNotFoundException() {
//        val login = "user2"
//        val sessionId = 1L
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user2
//
//        every {
//            sessionRepository.findByIdOrThrow(sessionId)
//        } throws SessionNotFoundException(sessionId)
//
//        assertThrows<SessionNotFoundException> { gameService.joinSession(login, sessionId) }
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            sessionRepository.findByIdOrThrow(sessionId)
//        }
//        verify(exactly = 0) {
//            sessionRepository.save(any())
//        }
//    }
//
//    @Test
//    fun whenJoinNotOpenedSession_thenThrowSessionNotOpenedException() {
//        val login = "user2"
//        val sessionId = 1L
//
//        every {
//            userRepository.findByLoginOrThrow(login)
//        } returns user2
//
//        every {
//            sessionRepository.findByIdOrThrow(sessionId)
//        } returns getSessionWithPlayer()
//
//        assertThrows<SessionNotOpenedException> { gameService.joinSession(login, sessionId) }
//
//        verify(exactly = 1) {
//            userRepository.findByLoginOrThrow(login)
//        }
//        verify(exactly = 1) {
//            sessionRepository.findByIdOrThrow(sessionId)
//        }
//        verify(exactly = 0) {
//            sessionRepository.save(any())
//        }
//    }
//
//    private infix fun FigureDto.at(address: String) = address to TileDto(address, this)
//}
