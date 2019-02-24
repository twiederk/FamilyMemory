package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `init card`() {
        // Arrange

        // Act
        val card = Card(1)

        // Assert
        assertThat(card.imageId).isNotNull()
    }
}