package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `init card`() {
        // Act
        val card = Card(1)

        // Assert
        assertThat(card.imageId).isNotNull()
        assertThat(card.face).isEqualTo(Face.Back)
        assertThat(card.state).isEqualTo(CardState.OnBoard)
    }

    @Test
    fun `flip card`() {
        // Arrange
        val card = Card(1)

        // Act
        card.flip()

        // Assert
        assertThat(card.face).isEqualTo(Face.Front)
    }
}