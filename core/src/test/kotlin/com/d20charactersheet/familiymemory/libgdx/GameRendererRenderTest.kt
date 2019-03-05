package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameRendererRenderTest {

    @Test
    fun `draw stage`() {

        // Arrange
        val underTest = GameRenderer(FamilyMemoryGame(), mock())
        underTest.stage = mock()
        Gdx.gl = mock()

        // Act
        underTest.render()

        // Assert
        inOrder(underTest.stage, Gdx.gl) {
            verify(Gdx.gl).glClearColor(0f, 0f, 0f, 1f)
            verify(Gdx.gl).glClear(GL20.GL_COLOR_BUFFER_BIT)
            verify(underTest.stage).act()
            verify(underTest.stage).draw()
        }
    }

    @Test
    fun `match cards = remove matching cards`() {

        // Arrange
        Gdx.gl = mock()
        Gdx.input = mock()
        Gdx.graphics = mock()

        val drawableMock: Drawable = mock()
        val imageFactory: ImageFactory = mock()
        whenever(imageFactory.createDrawable(any())).thenReturn(drawableMock)

        val underTest = GameRenderer(FamilyMemoryGame(), imageFactory)
        underTest.create(mock())
        underTest.imageCards.filter { it.card.imageId == 1 }.forEach { it.flip() }

        // Act
        underTest.render()

        // Assert
        assertThat(underTest.imageCards.flatMap { it.image.actions }).hasSize(2).satisfies { it is DelayAction }
    }

    @Test
    fun `not matching card = turn cards to facing back`() {
        // Arrange
        Gdx.gl = mock()
        Gdx.input = mock()
        Gdx.graphics = mock()

        val back: Drawable = mock()
        val front: Drawable = mock()
        val imageFactory: ImageFactory = mock()
        whenever(imageFactory.createDrawable("0_128.jpg")).thenReturn(back)
        whenever(imageFactory.createDrawable("1_128.jpg")).thenReturn(front)
        whenever(imageFactory.createDrawable("2_128.jpg")).thenReturn(front)
        whenever(imageFactory.createDrawable("3_128.jpg")).thenReturn(front)

        val familyMemoryGame = FamilyMemoryGame(2)
        val underTest = GameRenderer(familyMemoryGame, imageFactory)
        underTest.create(mock())
        val cardWithImage1 = underTest.imageCards.findLast { it.card.imageId == 1 }!!
        val cardWithImage2 = underTest.imageCards.findLast { it.card.imageId == 2 }!!
        cardWithImage1.flip()
        cardWithImage2.flip()

        // Act
        underTest.render()

        // Assert
        assertThat(underTest.imageCards.flatMap { it.image.actions }).hasSize(2).satisfies { it is DelayAction }
    }


}