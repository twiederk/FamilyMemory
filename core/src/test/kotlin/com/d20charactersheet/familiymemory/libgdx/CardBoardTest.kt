package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CardBoardTest {

    private val imageMock: Image = mock()
    private val imageFactory: ImageFactory = mock()

    @BeforeEach
    fun setUp() {
        whenever(imageFactory.createImage(any())).thenReturn(imageMock)
    }


    @Test
    fun `create card board with two arranged cards`() {

        // Act
        val cardBoard = CardBoard(FamilyMemoryGame(), imageFactory)

        // Assert
        assertThat(cardBoard.imageCards).hasSize(2)

        argumentCaptor<Float> {
            verify(imageMock, times(2)).setX(capture())
            assertThat(firstValue).isEqualTo(32.0f)
            assertThat(secondValue).isEqualTo(184.0f)
        }

        argumentCaptor<Float> {
            verify(imageMock, times(2)).setY(capture())
            assertThat(firstValue).isEqualTo(32.0f)
            assertThat(secondValue).isEqualTo(32.0f)
        }

    }

    @Test
    fun `create card board with four arranged cards`() {

        // Act
        val cardBoard = CardBoard(FamilyMemoryGame(2), imageFactory)

        // Assert
        assertThat(cardBoard.imageCards).hasSize(4)

        argumentCaptor<Float> {
            verify(imageMock, times(4)).setX(capture())
            assertThat(allValues[0]).isEqualTo(32.0f)
            assertThat(allValues[1]).isEqualTo(184.0f)
            assertThat(allValues[2]).isEqualTo(32.0f)
            assertThat(allValues[3]).isEqualTo(184.0f)
        }

        argumentCaptor<Float> {
            verify(imageMock, times(4)).setY(capture())
            assertThat(allValues[0]).isEqualTo(32.0f)
            assertThat(allValues[1]).isEqualTo(32.0f)
            assertThat(allValues[2]).isEqualTo(184.0f)
            assertThat(allValues[3]).isEqualTo(184.0f)
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