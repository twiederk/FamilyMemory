package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.Card
import com.d20charactersheet.familiymemory.domain.Face
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ImageCardTest {

    @Test
    fun flip() {
        val front: Drawable = mock()

        // Arrange
        val imageCard = ImageCard(Card(1), front, mock())

        // Act
        imageCard.flip()

        // Assert
        assertThat(imageCard.card.face).isEqualTo(Face.Front)
        assertThat(imageCard.image.drawable).isEqualTo(front)
    }

}