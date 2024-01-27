package dev.overwave.chess.misc

import dev.overwave.chess.readFile
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.context.junit.jupiter.SpringExtension

class CleanUpAfterEachTest : AfterEachCallback {
    override fun afterEach(extensionContext: ExtensionContext) {
        val applicationContext = SpringExtension.getApplicationContext(extensionContext)

        truncateTables(applicationContext)
        resetFactories(applicationContext)
    }

    private fun resetFactories(context: ApplicationContext) {
        val userFactory = context.getBean<TestUserFactory>()
        userFactory.reset()
    }

    private fun truncateTables(context: ApplicationContext) {
        val jdbcTemplate = context.getBean<NamedParameterJdbcTemplate>()
        val truncateSql = readFile("/database/truncate.sql")
        jdbcTemplate.update(truncateSql, MapSqlParameterSource())
    }
}
