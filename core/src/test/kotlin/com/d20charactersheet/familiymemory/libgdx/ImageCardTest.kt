package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.Action
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.Card
import com.d20charactersheet.familiymemory.domain.CardState
import com.d20charactersheet.familiymemory.domain.Face
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ImageCardTest {

    @Test
    fun init() {
        // Arrange
        val front: Drawable = mock()
        val back: Drawable = mock()
        val familyMemoryGame = FamilyMemoryGame()
        val card = familyMemoryGame.getCards()[0]

        // Act
        val imageCard = ImageCard(familyMemoryGame, card, front, back)

        // Assert
        assertThat(imageCard.image.x).isEqualTo(card.x)
        assertThat(imageCard.image.y).isEqualTo(card.y)
        assertThat(imageCard.image.drawable).isEqualTo(back)
    }

    @Test
    fun `flip card`() {

        // Arrange
        val front: Drawable = mock()
        val familyMemoryGame = FamilyMemoryGame()
        val card = familyMemoryGame.getCards()[0]
        val imageCard = ImageCard(familyMemoryGame, card, front, mock())

        // Act
        imageCard.flip()

        // Assert
        assertThat(imageCard.card.face).isEqualTo(Face.Front)
        assertThat(imageCard.image.drawable).isEqualTo(front)
    }


    @Nested
    inner class ImageCardUpdate {

        @Test
        fun `don't update if card is back facing`() {
            // Arrange
            val back: Drawable = mock()
            val front: Drawable = mock()
            val imageCard = ImageCard(mock(), Card(1), front, back)

            // Act
            imageCard.update()

            // Assert
            assertThat(imageCard.image.drawable).isEqualTo(back)
            assertThat(imageCard.image.actions).isEmpty()
        }

        @Test
        fun `update to hide matching card`() {
            // Arrange
            val card = Card(1)
            card.state = CardState.OffBoard
            val imageCard = ImageCard(mock(), card, mock(), mock())

            // Act
            imageCard.update()

            // Assert
            assertThat(imageCard.image.actions).hasSize(1)
            assertThat(imageCard.image.actions[0]).isInstanceOf(DelayAction::class.java)
        }

        @Test
        fun `update to turn not matching card to back facing`() {
            // Arrange
            val front: Drawable = mock()
            val back: Drawable = mock()
            val card = Card(1)
            card.state = CardState.OnBoard
            card.face = Face.Back
            val imageCard = ImageCard(mock(), card, front, back)
            imageCard.image.drawable = front

            // Act
            imageCard.update()

            // Assert
            assertThat(imageCard.image.actions).hasSize(1)
            assertThat(imageCard.image.actions[0]).isInstanceOf(DelayAction::class.java)
        }

        @Test
        fun `update, don't add action if action is already set`() {
            // Arrange
            val card = Card(1)
            card.state = CardState.OffBoard
            val imageCard = ImageCard(mock(), card, mock(), mock())
            val action: Action = mock()
            imageCard.image.addAction(action)

            // Act
            imageCard.update()

            // Assert
            assertThat(imageCard.image.actions).hasSize(1)
            assertThat(imageCard.image.actions[0]).isSameAs(action)
        }
    }
}