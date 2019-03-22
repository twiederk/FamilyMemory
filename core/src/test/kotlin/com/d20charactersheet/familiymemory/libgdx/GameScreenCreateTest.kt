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
    private val libGDXFactory: LibGDXFactory = mock(defaultAnswer = RETURNS_DEEP_STUBS)

    @BeforeEach
    fun setUp() {
        Gdx.input = mock()
        Gdx.files = mock()
        Gdx.graphics = mock()
        whenever(Gdx.graphics.width).thenReturn(480)
        whenever(Gdx.graphics.height).thenReturn(800)

        whenever(libGDXFactory.createDrawable(any())).thenReturn(drawableMock)
        whenever(libGDXFactory.createLabel(eq("Number of tries: 0"), any())).thenReturn(numberOfTriesMock)
        whenever(libGDXFactory.createLabel(eq("Memory Completed!!!"), any())).thenReturn(memoryCompletedMock)
        whenever(libGDXFactory.createTextButton(eq("New Game"), any(), eq("small"))).thenReturn(newGame)
        whenever(libGDXFactory.createStage()).thenReturn(stageMock)
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
            verify(libGDXFactory).createTextButton(capture(), any(), eq("small"))
            assertThat(firstValue).isEqualTo("New Game")
        }
        assertThat(underTest.menuRenderer.newGame).isNotNull
        verify(underTest.menuRenderer.newGame).setPosition(240f, 400f)
        verify(underTest.menuRenderer.newGame).isVisible = false
        verify(underTest.menuRenderer.newGame).addListener(any())

    }

    private fun assertImageCards(underTest: GameScreen) {
        assertImageCard(underTest.cardRenderer.imageCards[0], 32f, 32f)
        assertImageCard(underTest.cardRenderer.imageCards[1], 184f, 32f)
        assertImageCard(underTest.cardRenderer.imageCards[2], 32f, 184f)
        assertImageCard(underTest.cardRenderer.imageCards[3], 184f, 184f)
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
