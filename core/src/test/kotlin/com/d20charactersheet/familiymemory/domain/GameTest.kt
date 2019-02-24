package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {

    @Test
    internal fun `construct game`() {
        // Act
        val game = FamilyMemoryGame()

        // Assert
        val cards = game.cards
        assertThat(cards).hasSize(2)
        assertThat(cards[0].imageId).isEqualTo(1)
        assertThat(cards[1].imageId).isEqualTo(1)

    }

}