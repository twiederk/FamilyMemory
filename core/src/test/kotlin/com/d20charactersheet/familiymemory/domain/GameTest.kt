package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {

    @Test
    internal fun `construct game`() {
        // Act
        val familyMemoryGame = FamilyMemoryGame()

        // Assert
        assertThat(familyMemoryGame.getCards()).hasSize(2)
        assertThat(familyMemoryGame.getCards()[0].imageId).isEqualTo(1)
        assertThat(familyMemoryGame.getCards()[1].imageId).isEqualTo(1)
    }

}
