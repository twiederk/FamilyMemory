package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.Face
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameRendererClickListenerTest {

    @Test
    fun `no action is running = flip`() {
        // Arrange
        val imageCard: ImageCard = mock()
        val underTest = GameRenderer(mock(), mock())

        // Act
        underTest.flip(imageCard)

        // Assert
        verify(imageCard, only()).flip()
    }

    @Test
    fun `action is running = no flip`() {
        // Arrange
        val familyMemoryGame = FamilyMemoryGame()
        val card = familyMemoryGame.getCards()[0]
        val back: Drawable = mock()
        val imageCard = ImageCard(familyMemoryGame, card, mock(), back)
        imageCard.image.actions.add(mock())
        val underTest = GameRenderer(familyMemoryGame, mock())
        underTest.imageCards = mutableListOf(imageCard)

        // Act
        underTest.flip(imageCard)

        // Assert
        assertThat(card.face).isEqualTo(Face.Back)
        assertThat(imageCard.image.drawable).isEqualTo(back)
    }
}