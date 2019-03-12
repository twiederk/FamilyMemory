package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.CardState
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameRendererRenderTest {

    private lateinit var familyMemoryGame: FamilyMemoryGame
    private lateinit var underTest: GameRenderer


    @BeforeEach
    fun setUp() {
        Gdx.gl = mock()
        Gdx.input = mock()
        Gdx.graphics = mock()

        familyMemoryGame = FamilyMemoryGame(2)

        val back: Drawable = mock()
        val front: Drawable = mock()
        val imageFactory: ImageFactory = mock()
        whenever(imageFactory.createDrawable("0_128.jpg")).thenReturn(back)
        whenever(imageFactory.createDrawable("1_128.jpg")).thenReturn(front)
        whenever(imageFactory.createDrawable("2_128.jpg")).thenReturn(front)
        whenever(imageFactory.createDrawable("3_128.jpg")).thenReturn(front)

        underTest = GameRenderer(familyMemoryGame, imageFactory)
        underTest.stage = mock()
        underTest.numberOfTries = mock()
        underTest.memoryCompleted = mock()
        underTest.buildImageCards()
    }

    @Test
    fun `render memory completed`() {

        // Arrange
        underTest.imageCards.forEach { it.card.state = CardState.OffBoard }

        // Act
        underTest.render()

        // Assert
        inOrder(Gdx.gl, underTest.stage, underTest.numberOfTries, underTest.memoryCompleted) {
            verify(underTest.numberOfTries).setText("Number of tries: 0")
            verify(underTest.memoryCompleted).setText("Memory Completed!!!")
            verify(underTest.memoryCompleted).isVisible = true

            verify(Gdx.gl).glClearColor(0f, 0f, 0f, 1f)
            verify(Gdx.gl).glClear(GL20.GL_COLOR_BUFFER_BIT)
            verify(underTest.stage).act()
            verify(underTest.stage).draw()
        }
    }

    @Test
    fun `match cards = remove matching cards`() {

        // Arrange
        underTest.imageCards.filter { it.card.imageId == 1 }.forEach { it.flip() }

        // Act
        underTest.render()

        // Assert
        assertThat(underTest.imageCards.flatMap { it.image.actions }).hasSize(2).satisfies { it is DelayAction }
    }

    @Test
    fun `not matching card = turn cards to facing back`() {

        // Arrange
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