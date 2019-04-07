package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.d20charactersheet.familiymemory.domain.CardState
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameScreenRenderTest {

    private lateinit var familyMemoryGame: FamilyMemoryGame
    private lateinit var underTest: GameScreen


    @BeforeEach
    fun setUp() {
        Gdx.gl = mock()
        Gdx.input = mock()
        Gdx.graphics = mock()

        familyMemoryGame = FamilyMemoryGame(2)

        val back: Drawable = mock()
        val front: Drawable = mock()
        val libGDXFactory: LibGDXFactory = mock {
            on { createDrawable("0_128.jpg") }.thenReturn(back)
            on { createDrawable("1_128.jpg") }.thenReturn(front)
            on { createDrawable("2_128.jpg") }.thenReturn(front)
            on { createDrawable("3_128.jpg") }.thenReturn(front)
        }

        underTest = GameScreen(familyMemoryGame)
        underTest.libGDXFactory = libGDXFactory
        underTest.stage = mock()
        underTest.cardRenderer = CardRenderer(libGDXFactory)
        underTest.cardRenderer.buildImageCards(familyMemoryGame)
        underTest.menuRenderer = MenuRenderer(libGDXFactory)
        underTest.menuRenderer.numberOfTries = mock()
        underTest.menuRenderer.memoryCompleted = mock()
        underTest.menuRenderer.newGame = mock()
    }

    @Test
    fun `render memory completed`() {

        // Arrange
        underTest.cardRenderer.imageCards.forEach { it.card.state = CardState.OffBoard }

        // Act
        underTest.render(0f)

        // Assert
        inOrder(Gdx.gl, underTest.stage, underTest.menuRenderer.numberOfTries, underTest.menuRenderer.memoryCompleted, underTest.menuRenderer.newGame) {
            verify(underTest.menuRenderer.numberOfTries).setText("Number of tries: 0")
            verify(underTest.menuRenderer.memoryCompleted).isVisible = true
            verify(underTest.menuRenderer.newGame).isVisible = true

            verify(Gdx.gl).glClearColor(0f, 0f, 0f, 1f)
            verify(Gdx.gl).glClear(GL20.GL_COLOR_BUFFER_BIT)
            verify(underTest.stage).act()
            verify(underTest.stage).draw()
        }
    }

    @Test
    fun `match cards = remove matching cards`() {

        // Arrange
        underTest.cardRenderer.imageCards.filter { it.card.imageId == 1 }.forEach { it.flip() }

        // Act
        underTest.render(0f)

        // Assert
        assertThat(underTest.cardRenderer.imageCards.flatMap { it.image.actions }).hasSize(2).satisfies { it is DelayAction }
    }

    @Test
    fun `not matching card = turn cards to facing back`() {

        // Arrange
        val cardWithImage1 = underTest.cardRenderer.imageCards.findLast { it.card.imageId == 1 }!!
        val cardWithImage2 = underTest.cardRenderer.imageCards.findLast { it.card.imageId == 2 }!!
        cardWithImage1.flip()
        cardWithImage2.flip()

        // Act
        underTest.render(0f)

        // Assert
        assertThat(underTest.cardRenderer.imageCards.flatMap { it.image.actions }).hasSize(2).satisfies { it is DelayAction }
    }


}