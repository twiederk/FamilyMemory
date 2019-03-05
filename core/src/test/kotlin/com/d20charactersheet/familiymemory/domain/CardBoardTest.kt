package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class CardBoardTest {


    @Test
    fun `create card board with two arranged cards`() {

        // Act
        val cardBoard = CardBoard(1)

        // Assert
        with(cardBoard) {
            assertThat(cards).hasSize(2)

            assertThat(cards[0].x).isEqualTo(32.0f)
            assertThat(cards[0].y).isEqualTo(32.0f)

            assertThat(cards[1].x).isEqualTo(184.0f)
            assertThat(cards[1].y).isEqualTo(32.0f)
        }

    }

    @Test
    fun `create card board with four arranged cards`() {

        // Act
        val cardBoard = CardBoard(2)

        // Assert
        with(cardBoard) {
            assertThat(cards).hasSize(4)

            assertThat(cards[0].x).isEqualTo(32.0f)
            assertThat(cards[0].y).isEqualTo(32.0f)
            assertThat(cards[1].x).isEqualTo(184.0f)
            assertThat(cards[1].y).isEqualTo(32.0f)
            assertThat(cards[2].x).isEqualTo(32.0f)
            assertThat(cards[2].y).isEqualTo(184.0f)
            assertThat(cards[3].x).isEqualTo(184.0f)
            assertThat(cards[3].y).isEqualTo(184.0f)
        }

    }

    @Test
    fun `shuffle cards`() {

        // Act
        val cardBoard = CardBoard(4, random = Random(1))

        // Assert
        assertThat(cardBoard.cards.map { card -> card.imageId }).containsExactly(2, 4, 4, 1, 2, 1, 3, 3)
    }
}