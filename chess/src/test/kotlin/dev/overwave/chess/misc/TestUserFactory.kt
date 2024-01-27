package dev.overwave.chess.misc

import dev.overwave.chess.model.User
import dev.overwave.chess.repository.UserRepository
import org.springframework.boot.test.context.TestComponent
import java.util.concurrent.atomic.AtomicInteger

@TestComponent
class TestUserFactory(
    private val userRepository: UserRepository,
) {
    private val usersCount = AtomicInteger(0)

    private val userNameDictionary = listOf(
        "Zeus",
        "Hera",
        "Poseidon",
        "Demeter",
        "Apollo",
        "Artemis",
        "Ares",
        "Athena",
        "Hephaestus",
        "Aphrodite",
        "Hermes",
        "Hestia",
        "Dionysus",
    )

    fun createUser(index: Int = usersCount.getAndIncrement(), bot: Boolean = false): User {
        val name = userNameDictionary[index % userNameDictionary.size]
        val user = User("${name.lowercase()}_$index", name, name.hashCode().toString(), bot)
        return userRepository.save(user)
    }

    fun createUser(login: String): User {
        val name = login.replaceFirstChar { it.titlecase() }
        val user = User(login.lowercase(), name, login.hashCode().toString(), false)
        return userRepository.save(user)
    }

    fun reset() {
        userRepository.deleteAll()
        usersCount.set(0)
    }
}