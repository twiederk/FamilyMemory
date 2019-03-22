package com.d20charactersheet.familiymemory.libgdx

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class MenuRendererTest {

    @Test
    fun restart() {
        // Arrange
        val underTest = MenuRenderer(mock())
        underTest.memoryCompleted = mock()
        underTest.newGame = mock()

        // Act
        underTest.restart()

        // Assert
        verify(underTest.memoryCompleted).isVisible = false
        verify(underTest.newGame).isVisible = false
    }

}