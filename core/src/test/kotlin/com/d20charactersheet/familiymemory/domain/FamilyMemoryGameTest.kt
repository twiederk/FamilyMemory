package com.d20charactersheet.familiymemory.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class FamilyMemoryGameTest {

    @Nested
    inner class MatchingCards {

        @Test
        fun `match with 2 cards, both facing back = no match`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(1)

            // Act
            val match = familyMemoryGame.match()

            // Assert
            assertThat(match).isEmpty
        }

        @Test
        fun `match with 2 cards, one front facing and one back facing = no match`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(1)
            familyMemoryGame.getCards()[0].flip()

            // Act
            val match = familyMemoryGame.match()

            // Assert
            assertThat(match).isEmpty
        }

        @Test
        fun `match with 2 cards, two cards front facing = match`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(1)
            familyMemoryGame.getCards().forEach { it.flip() }

            // Act
            val match = familyMemoryGame.match()

            // Assert
            assertThat(match).isNotEmpty
            assertThat(match.get().first.imageId).isEqualTo(1)
            assertThat(match.get().second.imageId).isEqualTo(1)
        }

        @Test
        fun `match with 4 cards, two cards already matched and two front facing = match`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)
            familyMemoryGame.getCards().forEach { it.flip() }
            familyMemoryGame.getCards().filter { it.imageId == 1 }.forEach { it.state = CardState.OffBoard }

            // Act
            val match = familyMemoryGame.match()

            // Assert
            assertThat(match).isNotEmpty
            assertThat(match.get().first.imageId).isEqualTo(2)
            assertThat(match.get().second.imageId).isEqualTo(2)
        }

        @Test
        fun `match with 4 cards, two card front facing, but different image = no match`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)
            val cardWithImage1 = familyMemoryGame.getCards().findLast { it.imageId == 1 }
            val cardWithImage2 = familyMemoryGame.getCards().findLast { it.imageId == 2 }
            cardWithImage1?.flip()
            cardWithImage2?.flip()

            // Act
            val match = familyMemoryGame.match()

            // Assert
            assertThat(match).isNotEmpty
            assertThat(match.get().first.face).isEqualTo(Face.Back)
            assertThat(match.get().first.face).isEqualTo(Face.Back)
        }

    }

    @Nested
    inner class FlippingCards {

        @Test
        fun `flip card if zero other cards are flipped`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)

            // Act
            val flipped = familyMemoryGame.flip(familyMemoryGame.getCards()[0])

            // Assert
            assertThat(flipped).isTrue()
            assertThat(familyMemoryGame.getCards()[0].face).isEqualTo(Face.Front)
        }

        @Test
        fun `flip card if one other card is flipped`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)
            familyMemoryGame.getCards()[1].flip()

            // Act
            val flipped = familyMemoryGame.flip(familyMemoryGame.getCards()[0])

            // Assert
            assertThat(flipped).isTrue()
            assertThat(familyMemoryGame.getCards()[0].face).isEqualTo(Face.Front)
        }

        @Test
        fun `don't flip card if two other cards are already flipped`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)
            familyMemoryGame.getCards()[1].flip()
            familyMemoryGame.getCards()[2].flip()

            // Act
            val flipped = familyMemoryGame.flip(familyMemoryGame.getCards()[0])

            // Assert
            assertThat(flipped).isFalse()
            assertThat(familyMemoryGame.getCards()[0].face).isEqualTo(Face.Back)
        }

        @Test
        fun `flip card if three other cards are already flipped, but two are off board`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(2)
            familyMemoryGame.getCards()[1].flip()
            familyMemoryGame.getCards()[2].flip()
            familyMemoryGame.getCards()[2].state = CardState.OffBoard
            familyMemoryGame.getCards()[3].flip()
            familyMemoryGame.getCards()[3].state = CardState.OffBoard

            // Act
            val flipped = familyMemoryGame.flip(familyMemoryGame.getCards()[0])

            // Assert
            assertThat(flipped).isTrue()
            assertThat(familyMemoryGame.getCards()[0].face).isEqualTo(Face.Front)
        }

        @Test
        fun `don't flip already flipped cards`() {
            // Arrange
            val familyMemoryGame = FamilyMemoryGame(1)
            familyMemoryGame.getCards()[0].flip()

            // Act
            val flipped = familyMemoryGame.flip(familyMemoryGame.getCards()[0])

            // Assert
            assertThat(flipped).isFalse()
            assertThat(familyMemoryGame.getCards()[0].face).isEqualTo(Face.Front)
        }

    }

}