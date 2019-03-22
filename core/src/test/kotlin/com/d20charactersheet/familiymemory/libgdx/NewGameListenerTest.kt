package com.d20charactersheet.familiymemory.libgdx

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify

import org.junit.jupiter.api.Test

internal class NewGameListenerTest {

    @Test
    fun touchDown() {
        // Arrange
        val gameScreen: GameScreen = mock()

        // Act
        NewGameListener(gameScreen).touchDown(mock(), 0f, 0f, 0, 0)

        // Assert
        verify(gameScreen).restart(any())
    }
}