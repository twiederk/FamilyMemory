package com.d20charactersheet.familiymemory.libgdx

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class GameRendererDisposeTest {

    private val underTest = GameRenderer(mock())

    @Test
    fun `dispose resources`() {

        // Arrange
        underTest.stage = mock()

        // Act
        underTest.dispose()

        // Assert
        verify(underTest.stage).dispose()
    }
}