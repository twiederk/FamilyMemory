package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CardBoardTest {

    private val drawableMock: Drawable = mock()
    private val imageFactory: ImageFactory = mock()

    @BeforeEach
    fun setUp() {
        whenever(imageFactory.createDrawable(any())).thenReturn(drawableMock)
    }


    @Test
    fun `create card board with two arranged cards`() {

        // Act
        val cardBoard = CardBoard(FamilyMemoryGame(), imageFactory)

        // Assert
        assertThat(cardBoard.imageCards).hasSize(2)
        val firstImageCard = cardBoard.imageCards[0]
        assertThat(firstImageCard.front).isEqualTo(drawableMock)
        assertThat(firstImageCard.back).isEqualTo(drawableMock)
        assertThat(firstImageCard.image.x).isEqualTo(32.0f)
        assertThat(firstImageCard.image.y).isEqualTo(32.0f)

        val secondImageCard = cardBoard.imageCards[1]
        assertThat(secondImageCard.front).isEqualTo(drawableMock)
        assertThat(secondImageCard.back).isEqualTo(drawableMock)
        assertThat(secondImageCard.image.x).isEqualTo(184.0f)
        assertThat(secondImageCard.image.y).isEqualTo(32.0f)

    }

    @Test
    fun `create card board with four arranged cards`() {

        // Act
        val cardBoard = CardBoard(FamilyMemoryGame(2), imageFactory)

        // Assert
        assertThat(cardBoard.imageCards).hasSize(4)

        with(cardBoard) {
            assertThat(imageCards[0].image.x).isEqualTo(32.0f)
            assertThat(imageCards[0].image.y).isEqualTo(32.0f)
            assertThat(imageCards[1].image.x).isEqualTo(184.0f)
            assertThat(imageCards[1].image.y).isEqualTo(32.0f)
            assertThat(imageCards[2].image.x).isEqualTo(32.0f)
            assertThat(imageCards[2].image.y).isEqualTo(184.0f)
            assertThat(imageCards[3].image.x).isEqualTo(184.0f)
            assertThat(imageCards[3].image.y).isEqualTo(184.0f)
        }

    }

    @Test
    fun `shuffle cards`() {

        // Act
        val cardBoard = CardBoard(familyMemoryGame = FamilyMemoryGame(4), imageFactory = imageFactory, random = Random(1))

        // Assert
        assertThat(cardBoard.imageCards.map { imageCard -> imageCard.card.imageId }).containsExactly(2, 4, 4, 1, 2, 1, 3, 3)
    }
}