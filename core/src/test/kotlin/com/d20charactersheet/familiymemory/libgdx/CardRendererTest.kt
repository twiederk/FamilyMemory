package com.d20charactersheet.familiymemory.libgdx

import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardRendererTest {

    @Test
    fun restart() {
        // Arrange
        val libGDXFactory: LibGDXFactory = mock() {
            on { createDrawable(any()) }.thenReturn(mock())
        }
        val underTest = CardRenderer(libGDXFactory)

        // Act
        underTest.restart(FamilyMemoryGame(4), mock())

        // Assert
        assertThat(underTest.imageCards).hasSize(8)
    }
}