package dev.overwave.chess.misc

import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ContextConfiguration(
    classes = [TestConfiguration::class],
    initializers = [ConfigDataApplicationContextInitializer::class]
)
@ExtendWith(SpringExtension::class, CleanUpAfterEachTest::class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(
    provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY,
    refresh = AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_CLASS
)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
annotation class FunctionalTest
