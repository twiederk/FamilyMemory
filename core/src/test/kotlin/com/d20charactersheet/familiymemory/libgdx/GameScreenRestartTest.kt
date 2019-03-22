package com.d20charactersheet.familiymemory.libgdx

import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameScreenRestartTest {

    @Test
    fun restartGame() {
        // Arrange
        val gameScreen = GameScreen(mock())
        gameScreen.cardRenderer = mock()
        gameScreen.menuRenderer = mock()
        gameScreen.stage = mock()
        val newFamilyMemoryGame = FamilyMemoryGame()

        // Act
        gameScreen.restart(newFamilyMemoryGame)

        // Assert
        assertThat(gameScreen.familyMemoryGame).isEqualTo(newFamilyMemoryGame)
        verify(gameScreen.cardRenderer).restart(eq(newFamilyMemoryGame), any())
        verify(gameScreen.menuRenderer).restart()
    }

}