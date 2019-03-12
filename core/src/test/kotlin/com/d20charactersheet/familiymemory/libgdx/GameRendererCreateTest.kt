package com.d20charactersheet.familiymemory.libgdx

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import com.badlogic.gdx.utils.Align
import com.d20charactersheet.familiymemory.domain.FamilyMemoryGame
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GameRendererCreateTest {

    private val drawableMock: Drawable = mock()
    private val imageFactory: ImageFactory = mock()

    private val underTest = GameRenderer(FamilyMemoryGame(4), imageFactory)

    @BeforeEach
    fun setUp() {
        Gdx.input = mock()
        Gdx.files = mock()
        Gdx.graphics = mock()
        whenever(Gdx.graphics.width).thenReturn(480)
        whenever(Gdx.graphics.height).thenReturn(800)

        whenever(imageFactory.createDrawable(any())).thenReturn(drawableMock)
        whenever(imageFactory.createBitmapFont(any())).thenReturn(mock())
    }


    @Test
    fun `create stage`() {

        // Arrange
        val stage: Stage = mock()

        // Act
        underTest.create(stage)

        // Assert
        with(underTest.imageCards[0]) {
            assertThat(front).isEqualTo(drawableMock)
            assertThat(back).isEqualTo(drawableMock)
            assertThat(image.x).isEqualTo(32f)
            assertThat(image.y).isEqualTo(32f)
        }

        with(underTest.imageCards[1]) {
            assertThat(front).isEqualTo(drawableMock)
            assertThat(back).isEqualTo(drawableMock)
            assertThat(image.x).isEqualTo(184f)
            assertThat(image.y).isEqualTo(32f)
        }

        assertThat(underTest.numberOfTries).isNotNull
        with(underTest.numberOfTries) {
            assertThat(text).isEmpty()
            assertThat(labelAlign).isEqualTo(Align.center)
            assertThat(width).isEqualTo(480f)
            assertThat(height).isEqualTo(40f)
            assertThat(x).isEqualTo(0f)
            assertThat(y).isEqualTo(760f)
            assertThat(isVisible).isTrue()
        }

        assertThat(underTest.memoryCompleted).isNotNull
        with(underTest.memoryCompleted) {
            assertThat(text).isEmpty()
            assertThat(labelAlign).isEqualTo(Align.center)
            assertThat(width).isEqualTo(480f)
            assertThat(height).isEqualTo(40f)
            assertThat(x).isEqualTo(0f)
            assertThat(y).isEqualTo(720f)
            assertThat(isVisible).isFalse()
        }

        verify(stage, times(10)).addActor(any())
        verify(Gdx.graphics).isContinuousRendering = false
        verify(Gdx.graphics).requestRendering()
    }

}
