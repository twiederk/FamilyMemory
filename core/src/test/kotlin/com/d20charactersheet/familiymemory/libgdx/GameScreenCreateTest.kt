package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.RETURNS_DEEP_STUBS

internal class GameScreenCreateTest {

    private val drawableMock: Drawable = mock()
    private val numberOfTriesMock: Label = mock()
    private val memoryCompletedMock: Label = mock()
    private val newGame: TextButton = mock()
    private val stageMock: Stage = mock()
    private val libGDXFactory: LibGDXFactory = mock(defaultAnswer = RETURNS_DEEP_STUBS) {
        on { createDrawable(any()) }.thenReturn(drawableMock)
        on { createLabel(eq("Number of tries: 0"), any()) }.thenReturn(numberOfTriesMock)
        on { createLabel(eq("Memory Completed!!!"), any()) }.thenReturn(memoryCompletedMock)
        on { createTextButton(eq("New Game"), any()) }.thenReturn(newGame)
        on { createStage() }.thenReturn(stageMock)

    }

    @BeforeEach
    fun setUp() {
        Gdx.input = mock()
        Gdx.files = mock()
        Gdx.graphics = mock {
            on { width }.thenReturn(480)
            on { height }.thenReturn(800)
        }
    }


    @Test
    fun `create stage`() {

        // Act
        val underTest = GameScreen(FamilyMemoryGame(4), libGDXFactory)

        // Assert
        assertImageCards(underTest)
        assertGUI(underTest)

        verify(stageMock, times(11)).addActor(any())
        verify(Gdx.graphics).isContinuousRendering = false
        verify(Gdx.graphics).requestRendering()
    }

    private fun assertGUI(underTest: GameScreen) {
        argumentCaptor<String>().apply {
            verify(libGDXFactory, times(2)).createLabel(capture(), any())
            assertThat(firstValue).isEqualTo("Number of tries: 0")
            assertThat(secondValue).isEqualTo("Memory Completed!!!")
        }
        assertLabel(underTest.menuRenderer.numberOfTries, 480f, 40f, 0f, 760f)
        assertLabel(underTest.menuRenderer.memoryCompleted, 480f, 40f, 0f, 720f)
        verify(underTest.menuRenderer.memoryCompleted).isVisible = false

        argumentCaptor<String>().apply {
            verify(libGDXFactory).createTextButton(capture(), any())
            assertThat(firstValue).isEqualTo("New Game")
        }
        assertThat(underTest.menuRenderer.newGame).isNotNull
        verify(underTest.menuRenderer.newGame).setPosition(240f, 400f)
        verify(underTest.menuRenderer.newGame).isVisible = false
        verify(underTest.menuRenderer.newGame).addListener(any())

    }

    private fun assertImageCards(underTest: GameScreen) {
        assertImageCard(underTest.imageCards.imageCards[0], 32f, 32f)
        assertImageCard(underTest.imageCards.imageCards[1], 312f, 32f)
        assertImageCard(underTest.imageCards.imageCards[2], 32f, 312f)
        assertImageCard(underTest.imageCards.imageCards[3], 312f, 312f)
    }

    private fun assertImageCard(imageCard: ImageCard, x: Float, y: Float) {
        with(imageCard) {
            assertThat(front).isEqualTo(drawableMock)
            assertThat(back).isEqualTo(drawableMock)
            assertThat(image.x).isEqualTo(x)
            assertThat(image.y).isEqualTo(y)
        }
    }

    private fun assertLabel(label: Label, width: Float, height: Float, x: Float, y: Float) {
        assertThat(label).isNotNull
        verify(label).setSize(width, height)
        verify(label).setPosition(x, y)
        verify(label).setAlignment(Align.center)
    }


}
