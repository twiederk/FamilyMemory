package com.d20charactersheet.familiymemory

import com.d20charactersheet.familiymemory.libgdx.GameRenderer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class FamilyMemoryMainTest {

    @Test
    fun render() {
        // Arrange
        val gameRenderer: GameRenderer = mock()
        val familyMemoryMain = FamilyMemoryMain(gameRenderer)

        // Act
        familyMemoryMain.render()

        // Assert
        verify(gameRenderer, only()).render()
    }

    @Test
    fun dispose() {
        // Arrange
        val gameRenderer: GameRenderer = mock()
        val familyMemoryMain = FamilyMemoryMain(gameRenderer)

        // Act
        familyMemoryMain.dispose()

        // Assert
        verify(gameRenderer, only()).dispose()
    }
}